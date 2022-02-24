import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class Loader {

    protected static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    protected static SimpleDateFormat visitDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    protected static HashMap<Integer, WorkTime> voteStationWorkTimes = new HashMap<>();
    protected static HashMap<Voter, Integer> voterCounts = new HashMap<>();

    public static void main(String[] args) throws Exception {
        String fileName = "res/data-18M.xml";


//        for (int i = 0; i < 5; i++) {
//            SAXParserFactory factory = SAXParserFactory.newInstance();
//            SAXParser parser = factory.newSAXParser();
//            XMLHandler handler = new XMLHandler();
//
//            long memoryUsage;
//
//            memoryUsage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
//            parser.parse(new File(fileName), handler);
//            memoryUsage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - memoryUsage;
//            System.out.println("SAXParser memory usage: " + memoryUsage);
////        printingResults();
//
//            voterCounts.clear();
//            voteStationWorkTimes.clear();
//            System.gc();

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            DOMParser domParser = new DOMParser();

//            memoryUsage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long start = System.currentTimeMillis();
        domParser.parseFile(fileName, db);
        System.out.println("Duration time: " + (System.currentTimeMillis() - start));
            DBConnection.printVoterCounts();
//            memoryUsage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - memoryUsage;
//            System.out.println("DOMParser memory usage: " + memoryUsage);
////        printingResults();
//
//            voterCounts.clear();
//            voteStationWorkTimes.clear();
//            System.gc();
//        }
    }

    private static void printingResults() {
        System.out.println("Voting station work times: ");
        for (Integer votingStation : voteStationWorkTimes.keySet()) {
            WorkTime workTime = voteStationWorkTimes.get(votingStation);
            System.out.println("\t" + votingStation + " - " + workTime);
        }

        System.out.println("Duplicated voters: ");
        for (Voter voter : voterCounts.keySet()) {
            Integer count = voterCounts.get(voter);
            if (count > 1) {
                System.out.println("\t" + voter + " - " + count);
            }
        }
    }


}