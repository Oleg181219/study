import Metro.Line;
import Metro.Station;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MoscowMetro {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static JSONObject dataMetroJSON = new JSONObject();
    public static JSONObject stationsJSON = new JSONObject();
    public static JSONArray connectionsJSON = new JSONArray();
    public static JSONArray linesJSON = new JSONArray();

    public static void main(String[] args) throws IOException {
        String numLine = null;
        String lineName = null;
        String stationName = null;
        Station stationFirst = null;
        Station stationSecond;


        ArrayList<Station> stations = new ArrayList<>();
        Line lines;
        ArrayList<String> demoLine = new ArrayList<>();
        ArrayList<Line> line = new ArrayList<>();
        ArrayList<Cloneable> msKMetro = new ArrayList<>();
        HashMap<String, ArrayList<String>> stationMsk = new HashMap<>();
        ArrayList<ArrayList<Station>> subwayTransfer = new ArrayList<>();
        ArrayList<Station> crossStation;
        ArrayList<String> crossLines = new ArrayList<>();

        try {
            Document doc = Jsoup.connect("https://www.moscowmap.ru/metro.html#lines").maxBodySize(0).get();
            Elements links = doc.select("span[class]");
            String string = links.toString();
            String[] strings = string.split("\n");

            for (String s : strings) {

                if (s.contains("data-line=\"")) {
                    String[] lineString = s.split("\"");
                    lineName = lineString[4].substring(lineString[4].indexOf('>') + 1, lineString[4].indexOf('<'));
                    numLine = lineString[3];
                    demoLine.add(numLine);
                    lines = new Line(numLine, lineName);
                    line.add(lines);
                }

                if (s.contains("<span class=\"name\"")) {
                    stationName = s.substring(s.indexOf('>') + 1, s.lastIndexOf('<'));
                    Station station = new Station(numLine, stationName);
                    stations.add(station);
                }
            }
            for (String temp : demoLine) {
                ArrayList<String> temp2 = new ArrayList<>();
                for (Station station : stations) {
                    if (temp.equals(station.getLine())) {
                        temp2.add(station.getName());
                    }
                }
                stationMsk.put(temp, temp2);
            }
// переходы
            for (String s : strings) {
                if (s.contains("data-line=\"")) {
                    String[] lineString = s.split("\"");
                    numLine = lineString[3];
                    crossLines.add(numLine);
                }
                if (s.contains("\"name\"")) {
                    stationName = s.substring(s.indexOf('>') + 1, s.lastIndexOf('<'));
                    stationFirst = new Station(numLine, stationName);

                }
                if (s.contains("\"t-icon-metroln ln")) {
                    String nameCross = s.substring(s.indexOf('«') + 1, s.lastIndexOf('»'));
                    String[] lineString = s.split("\"");
                    int index = lineString[1].lastIndexOf('-') + 1;
                    String lineCross = lineString[1].substring(index);

                    if (!crossLines.contains(lineCross)) {
                        crossStation = new ArrayList<>();
                        stationSecond = new Station(lineCross, nameCross);
                        crossStation.add(stationFirst);
                        crossStation.add(stationSecond);
                        subwayTransfer.add(crossStation);
                    }

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject obj = new JSONObject();
        obj.put("station", stationMsk); // линии со станциями
        obj.put("line", line);// только линии номера и имя
        obj.put("connections", subwayTransfer);// переходы
        String json = GSON.toJson(obj);

        try (FileWriter file = new FileWriter("src/main/resources/map2.json")) {
            file.write(json);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
//============================
        try {
            JSONParser parser = new JSONParser();
            dataMetroJSON = (JSONObject) parser.parse(getJsonFile("src/main/resources/map2.json"));
            stationsJSON = (JSONObject) dataMetroJSON.get("station");
            linesJSON = (JSONArray) dataMetroJSON.get("line");
            connectionsJSON = (JSONArray) dataMetroJSON.get("connections");

            System.out.println("==============================\nВывод информации из JSON файла\n==============================");

            stationsJSON.keySet().forEach(lineNumber ->
            {
                //получаем имя станции из объекта lines JSON файла по номеру из объекта stations JSON
                String lineName1 = "";
                for (Object itemObject : linesJSON) {
                    if ((((JSONObject) itemObject).get("number")).equals(lineNumber.toString()))
                        lineName1 = (String) ((JSONObject) itemObject).get("name");
                }

                //получаем массив станции одной ветки
                JSONArray stationsArray = (JSONArray) stationsJSON.get(lineNumber);
                System.out.println("Количество станций на линии " + lineNumber + ": \"" + lineName1 + "\" - " + stationsArray.size());

            });

            //количество переходов
            System.out.println("Количество переходов: " + connectionsJSON.size());
        } catch (Exception e) {
            System.out.println("Ошибка при обработке файла");

        }


        msKMetro.add(stationMsk);
        msKMetro.add(line);
        msKMetro.add(subwayTransfer);

        try (FileWriter file = new FileWriter("src/main/resources/map.json")) {
            file.write(GSON.toJson(msKMetro));
        }

    }

    private static String getJsonFile(String path) {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            lines.forEach(line -> builder.append(line));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}

