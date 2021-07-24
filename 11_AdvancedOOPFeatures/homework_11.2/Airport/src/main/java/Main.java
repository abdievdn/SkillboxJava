import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {


    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
        //TODO Метод должден вернуть список рейсов вылетающих в ближайшие два часа.
        List<Flight> nextTwoHoursFlights = new ArrayList<>();
        Date time = new Date();
        long departureTime = 1000 * 60 * 60 * 2;

        airport.getTerminals()
                .stream()
                .map(Terminal::getFlights)
                .filter(flight -> flight.getType() == Flight.Type.DEPARTURE &&
                      (flight.getDate().compareTo(time) >= 0 && flight.getDate().compareTo(time) <= departureTime))
                .reduce(t -> nextTwoHoursFlights.add(t));

        return nextTwoHoursFlights;
    }

}

//.filter(flight -> flight.getType() == Flight.Type.DEPARTURE &&
//(flight.getDate().compareTo(time) >= 0 && flight.getDate().compareTo(time) <= departureTime))
