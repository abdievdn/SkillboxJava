import core.Line;
import core.Station;
import junit.framework.TestCase;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class TestGetShortestRoute extends TestCase {

    private static final String DATA_FILE = "src/main/resources/map.json";
    private StationIndex stationIndex;
    private RouteCalculator calculator = getRouteCalculator();

    private List<Station> routeWithoutTransfers;
    private List<Station> routeWithOneTransfer;
    private List<Station> routeWithTwoTransfers;

    private Station station1;
    private Station station2;

    private RouteCalculator getRouteCalculator() {
        createStationIndex();
        return new RouteCalculator(stationIndex);
    }

    @Override
    protected void setUp() throws Exception {

        routeWithoutTransfers = new ArrayList<>() {{
            add(stationIndex.getStation("Горьковская"));
            add(stationIndex.getStation("Невский проспект"));
            add(stationIndex.getStation("Сенная площадь"));
        }};

        routeWithOneTransfer = new ArrayList<>() {{
            add(stationIndex.getStation("Горьковская"));
            add(stationIndex.getStation("Невский проспект"));
            add(stationIndex.getStation("Гостиный двор"));
            add(stationIndex.getStation("Маяковская"));
            add(stationIndex.getStation("Площадь Александра Невского"));
            add(stationIndex.getStation("Елизаровская"));
        }};

        routeWithTwoTransfers = new ArrayList<>() {{
            add(stationIndex.getStation("Горьковская"));
            add(stationIndex.getStation("Невский проспект"));
            add(stationIndex.getStation("Гостиный двор"));
            add(stationIndex.getStation("Маяковская"));
            add(stationIndex.getStation("Площадь Восстания"));
            add(stationIndex.getStation("Чернышевская"));
        }};
    }

    public void testShortestRouteWithoutTransfers() {
        station1 = stationIndex.getStation("Горьковская");
        station2 = stationIndex.getStation("Сенная площадь");
        List<Station> expected = routeWithoutTransfers;
        List<Station> actual = calculator.getShortestRoute(station1, station2);
        assertEquals(expected, actual);
    }

    public void testShortestRouteWithOneTransfer() {
        station1 = stationIndex.getStation("Горьковская");
        station2 = stationIndex.getStation("Елизаровская");
        List<Station> expected = routeWithOneTransfer;
        List<Station> actual = calculator.getShortestRoute(station1, station2);
        assertEquals(expected, actual);
    }

    public void testShortestRouteWithTwoTransfers() {
        station1 = stationIndex.getStation("Горьковская");
        station2 = stationIndex.getStation("Чернышевская");
        List<Station> expected = routeWithTwoTransfers;
        List<Station> actual = calculator.getShortestRoute(station1, station2);
        assertEquals(expected, actual);
    }

    @Override
    protected void tearDown() throws Exception {

    }

    private void createStationIndex() {
        stationIndex = new StationIndex();
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonData = (JSONObject) parser.parse(getJsonFile());

            JSONArray linesArray = (JSONArray) jsonData.get("lines");
            parseLines(linesArray);

            JSONObject stationsObject = (JSONObject) jsonData.get("stations");
            parseStations(stationsObject);

            JSONArray connectionsArray = (JSONArray) jsonData.get("connections");
            parseConnections(connectionsArray);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void parseConnections(JSONArray connectionsArray) {
        connectionsArray.forEach(connectionObject ->
        {
            JSONArray connection = (JSONArray) connectionObject;
            List<Station> connectionStations = new ArrayList<>();
            connection.forEach(item ->
            {
                JSONObject itemObject = (JSONObject) item;
                int lineNumber = ((Long) itemObject.get("line")).intValue();
                String stationName = (String) itemObject.get("station");

                Station station = stationIndex.getStation(stationName, lineNumber);
                if (station == null) {
                    throw new IllegalArgumentException("core.Station " +
                            stationName + " on line " + lineNumber + " not found");
                }
                connectionStations.add(station);
            });
            stationIndex.addConnection(connectionStations);
        });
    }

    private void parseStations(JSONObject stationsObject) {
        stationsObject.keySet().forEach(lineNumberObject ->
        {
            int lineNumber = Integer.parseInt((String) lineNumberObject);
            Line line = stationIndex.getLine(lineNumber);
            JSONArray stationsArray = (JSONArray) stationsObject.get(lineNumberObject);
            stationsArray.forEach(stationObject ->
            {
                Station station = new Station((String) stationObject, line);
                stationIndex.addStation(station);
                line.addStation(station);
            });
        });
    }

    private void parseLines(JSONArray linesArray) {
        linesArray.forEach(lineObject -> {
            JSONObject lineJsonObject = (JSONObject) lineObject;
            Line line = new Line(
                    ((Long) lineJsonObject.get("number")).intValue(),
                    (String) lineJsonObject.get("name")
            );
            stationIndex.addLine(line);
        });
    }

    private String getJsonFile() {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(DATA_FILE));
            lines.forEach(line -> builder.append(line));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return builder.toString();
    }
}
