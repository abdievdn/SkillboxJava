import core.Line;
import core.Station;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.*;

public class TestGetRoute extends TestCase {
    private List<Station> route = new ArrayList<>();
    private Map<Station, TreeSet<Station>> connections = new TreeMap<>();
    private TreeSet<Station> stations1 = new TreeSet<>();
    private TreeSet<Station> stations2 = new TreeSet<>();
    private TreeSet<Station> stations3 = new TreeSet<>();

    Line line1 = new Line(1, "First");
    Line line2 = new Line(2, "Second");
    Line line3 = new Line(3, "Third");
    Line line4 = new Line(4, "Fourth");

    Station station11 = new Station("1-1", line1);
    Station station12 = new Station("1-2", line1);
    Station station13 = new Station("1-3", line1);
    Station station14 = new Station("1-4", line1);

    Station station21 = new Station("2-1", line2);
    Station station22 = new Station("2-2", line2);

    Station station31 = new Station("3-1", line3);
    Station station32 = new Station("3-2", line3);

    Station station41 = new Station("4-1", line4);
    Station station42 = new Station("4-2", line4);

    @Override
    protected void setUp() throws Exception {

        stations1.add(station12);
        stations1.add(station21);
        stations1.add(station31);
        connections.put(station12, stations1);

        stations2.add(station14);
        stations2.add(station41);
        connections.put(station14, stations2);

        stations3.add(station32);
        stations3.add(station42);
        connections.put(station32, stations1);

        route.add(station11);
        route.add(station12);
        route.add(station31);
        route.add(station32);
        route.add(station42);
    }

    @Test
    public void testGetShortestRoute() {
        List<Station> actual = RouteCalculator.getShortestRoute(station11, station42);
        List<Station> expected = route;
        assertEquals(expected, actual);
    }

    @Override
    protected void tearDown() throws Exception {

    }
}
