import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import java.io.File;
import java.util.Date;

public class DOMParser {

    public void parseFile(String fileName, DocumentBuilder db) throws Exception {
        Document doc = db.parse(new File(fileName));
        findEqualVoters(doc);
//        fixWorkTimes(doc);
    }

    private void findEqualVoters(Document doc) throws Exception {
        NodeList voters = doc.getElementsByTagName("voter");
        int votersCount = voters.getLength();
        for (int i = 0; i < votersCount; i++) {

            Node node = voters.item(i);
            NamedNodeMap attributes = node.getAttributes();

            String name = attributes.getNamedItem("name").getNodeValue();
            String birthDay = attributes.getNamedItem("birthDay").getNodeValue();

            DBConnection.countVoter(name, birthDay);

//            Voter voter = new Voter(name, birthDay);
//            Integer count = Loader.voterCounts.get(voter);
//            Loader.voterCounts.put(voter, count == null ? 1 : count + 1);
        }
        DBConnection.flush();
    }

    private void fixWorkTimes(Document doc) throws Exception {
        NodeList visits = doc.getElementsByTagName("visit");
        int visitCount = visits.getLength();
        for (int i = 0; i < visitCount; i++) {
            Node node = visits.item(i);
            NamedNodeMap attributes = node.getAttributes();

            Integer station = Integer.parseInt(attributes.getNamedItem("station").getNodeValue());
            Date time = Loader.visitDateFormat.parse(attributes.getNamedItem("time").getNodeValue());
            WorkTime workTime = Loader.voteStationWorkTimes.get(station);
            if (workTime == null) {
                workTime = new WorkTime();
                Loader.voteStationWorkTimes.put(station, workTime);
            }
            workTime.addVisitTime(time.getTime());
        }
    }
}
