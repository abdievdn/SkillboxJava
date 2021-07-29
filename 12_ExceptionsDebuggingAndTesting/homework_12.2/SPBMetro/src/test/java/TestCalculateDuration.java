import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class TestCalculateDuration extends TestCase {

    List<Station> route;

    @Override
    protected void setUp() throws Exception {

        route = new ArrayList<>();
        Line line1 = new Line(1, "First");
        Line line2 = new Line(2, "Second");

        route.add(new Station("1-1", line1));
        route.add(new Station("1-2", line1));
        route.add(new Station("2-1", line2));
        route.add(new Station("2-2", line2));
    }

    public void testCalculateDuration() {
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 8.5;
        assertEquals(expected, actual);
    }

    @Override
    protected void tearDown() throws Exception {

    }
}
