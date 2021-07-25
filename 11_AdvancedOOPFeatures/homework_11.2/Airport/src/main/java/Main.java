import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


public class Main {

    public static final int TWO_HOURS = 1000 * 60 * 60 * 2;

    public static void main(String[] args) {

    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
        //TODO Метод должден вернуть список рейсов вылетающих в ближайшие два часа.

        Date timeNow = new Date();
        int departureTime = timeNow.getDate() + TWO_HOURS;
        return airport.getTerminals()
                .stream()
                .map(Terminal::getFlights)
                .flatMap(Collection::stream)
                .filter(flight -> flight.getType() == Flight.Type.DEPARTURE &&
                       (flight.getDate().getTime() - timeNow.getTime() >= 0 && flight.getDate().getTime() - timeNow.getTime() <= departureTime))
                .collect(Collectors.toList());
    }

}
