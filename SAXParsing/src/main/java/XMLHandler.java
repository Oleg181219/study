import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.concurrent.ForkJoinPool;

public class XMLHandler extends DefaultHandler {

    private static HashMap<Integer, Voter> voterHashMap = new HashMap<>();
    private static ForkJoinPool forkJoinPool = new ForkJoinPool(2);
    private static ForkTask task;
    String name = "";
    String birthDay = "";
    String station = "";
    String time = "";
    Boolean endVoter = false;
    Boolean endVisit = false;
    Voter voter;
    int i = 0;
    int z = 0;
    int count = 0;


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)  {
        try {
            if (qName.equals("voter")) {
                name = attributes.getValue("name");
                birthDay = attributes.getValue("birthDay");


                endVoter = true;
            }
            if (qName.equals("visit")) {
                i = i + 1;
                station = attributes.getValue("station");
                time = attributes.getValue("time");
                voterHashMap.put(i, voter);
                voterHashMap.put(i, new Voter(name, birthDay, station, time));
                count +=1;
                endVisit = true;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (endVisit && endVoter) {
            try {
                endVoter = false;
                endVisit = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (voterHashMap.size() == 100000) {
            i = 0;
            System.out.println("номер fork - " + (++z) );
            for (int j = 1; j < voterHashMap.size()+ 1; j++) {
                String name = voterHashMap.get(j).getName();
                String bD = voterHashMap.get(j).getBirthDay();
                String station = voterHashMap.get(j).getStation();
                String time = voterHashMap.get(j).getTime();
                DBConnection.countVoter(name, bD, station, time);
            }
            System.out.println(DBConnection.getInsertQueryLenth());

            while ((task != null) && !task.isDone()) {
            }
            try {
                task = new ForkTask(DBConnection.getInsertQuery());
                forkJoinPool.execute(task);
                voterHashMap.clear();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void endDocument()  {
        System.out.println("voterHash = " + voterHashMap.size());
        for (int jj = 1; jj < voterHashMap.size(); jj++) {
            String name = voterHashMap.get(jj).getName();
            String bD = voterHashMap.get(jj).getBirthDay();
            String station = voterHashMap.get(jj).getStation();
            String time = voterHashMap.get(jj).getTime();
            DBConnection.countVoter(name, bD, station, time);
        }
        try {
            while ((task != null) && !task.isDone()) {
            }
            task = new ForkTask(DBConnection.getInsertQuery());
            forkJoinPool.execute(task);
            System.out.println("voterHashMap.size()" + voterHashMap.size());
            System.out.println("всего записей:"+ count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
