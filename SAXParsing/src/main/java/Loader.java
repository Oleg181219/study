
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.HashMap;



public class Loader {

    private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private static SimpleDateFormat visitDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    private static HashMap<Integer, WorkTime> voteStationWorkTimes = new HashMap<>();


    public static void main(String[] args) throws Exception {
        /**
         * 12233891
         * выбор файла для парсинга
         */
//        String fileName = "resources/data-0.2M.xml";
//        String fileName = "resources/data-1M.xml";
//        String fileName = "resources/data-18M.xml";
        String fileName = "resources/data-1572M.xml";

        long time = System.currentTimeMillis();
        long usage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();


        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        XMLHandler handler = new XMLHandler();

        /**
         * инициализация подключения к базе
         * создание таблицы
         */
        DBConnection.getConnection();

        /**
         * запуск SAX-парсинга
         */
        saxParser.parse(new File(fileName), handler);
        System.out.println("memory usage = " + ((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) - usage));
        System.out.println("time  = " + (System.currentTimeMillis() - time));


    }
}
