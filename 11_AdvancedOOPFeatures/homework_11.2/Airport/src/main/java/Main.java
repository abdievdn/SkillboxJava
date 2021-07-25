import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {

    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
        //TODO Метод должден вернуть список рейсов вылетающих в ближайшие два часа.
        List<Flight> nextTwoHoursFlights;
        Date time = new Date();
        int departureTime = time.getDate() + (1000 * 60 * 60 * 2);

        nextTwoHoursFlights = airport.getTerminals()
                .stream()
                .map(Terminal::getFlights)
                .flatMap(Collection::stream)
                .filter(flight -> flight.getType() == Flight.Type.DEPARTURE &&
                      (flight.getDate().compareTo(time) >= 0 && flight.getDate().compareTo(time) <= departureTime))
                .collect(Collectors.toList());

        return nextTwoHoursFlights;
    }

}
