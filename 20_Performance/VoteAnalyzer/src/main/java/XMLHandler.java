import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

public class XMLHandler extends DefaultHandler {

//    private Voter voter;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        try {
            if (qName.equals("voter")) {
                String name = attributes.getValue("name");
                String birthDay = attributes.getValue("birthDay");
                DBConnection.countVoter(name, birthDay);
            }

//            if (qName.equals("voter") && voter == null) {
//                Date birthDay = Loader.birthDayFormat.parse(attributes.getValue("birthDay"));
//                voter = new Voter(attributes.getValue("name"), birthDay);
//            }
//            else if (qName.equals("visit") && voter != null) {
//                //findEqualVoters
//                int count = Loader.voterCounts.getOrDefault(voter, 0);
//                Loader.voterCounts.put(voter, count + 1);

                //fixWorkTimes
//                int station = Integer.parseInt(attributes.getValue("station"));
//                Date time = Loader.visitDateFormat.parse(attributes.getValue("time"));
//                WorkTime workTime = Loader.voteStationWorkTimes.get(station);
//                if (workTime == null) {
//                    workTime = new WorkTime();
//                    Loader.voteStationWorkTimes.put(station, workTime);
//                }
//                workTime.addVisitTime(time.getTime());
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public void endElement(String uri, String localName, String qName) {
//
////        if (qName.equals("voter")) {
////            voter = null;
////        }
//    }

    @Override
    public void endDocument() throws SAXException {
        try {
            DBConnection.flush();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
