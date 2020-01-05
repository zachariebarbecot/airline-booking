package unit.flights;

import com.zbar.exercises.airline.booking.adapters.secondaries.gateways.inmemories.repositories.InMemoryFlightRepository;
import com.zbar.exercises.airline.booking.core.entities.Country;
import com.zbar.exercises.airline.booking.core.entities.Flight;
import com.zbar.exercises.airline.booking.core.entities.FlightId;
import com.zbar.exercises.airline.booking.core.gateways.repositories.FlightRepository;
import com.zbar.exercises.airline.booking.core.usecases.flights.retrieval.RetrieveFlights;
import com.zbar.exercises.airline.booking.core.usecases.flights.retrieval.FlightDTO;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class RetrieveFlightsTest {

    private final FlightRepository flightRepository = new InMemoryFlightRepository();

    FlightDTO parisDTO = new FlightDTO(
            new FlightId("PRS-LNDN"),
            Country.FR,
            Country.EN,
            LocalDateTime.of(2020, Month.JANUARY, 1, 0, 0),
            LocalDateTime.of(2020, Month.JANUARY, 2, 0, 0),
            250
    );
    FlightDTO londonDTO = new FlightDTO(
            new FlightId("LNDN-PRS"),
            Country.EN,
            Country.FR,
            LocalDateTime.of(2020, Month.JANUARY, 3, 0, 0),
            LocalDateTime.of(2020, Month.JANUARY, 4, 0, 0),
            100
    );

    @Test
    public void shouldNotRetrieveAnyFlight() {
        assertThat(this.retrieve()).isEmpty();
    }

    @Test
    public void shouldRetrieveAllOfThem() {
        var travels = new HashSet<Flight>();
        var paris = new Flight(
                new FlightId("PRS-LNDN"),
                Country.FR,
                Country.EN,
                LocalDateTime.of(2020, Month.JANUARY, 1, 0, 0),
                LocalDateTime.of(2020, Month.JANUARY, 2, 0, 0),
                250
        );
        var london = new Flight(
                new FlightId("LNDN-PRS"),
                Country.EN,
                Country.FR,
                LocalDateTime.of(2020, Month.JANUARY, 3, 0, 0),
                LocalDateTime.of(2020, Month.JANUARY, 4, 0, 0),
                100
        );
        travels.add(paris);
        travels.add(london);
        this.flightRepository.saveAll(travels);
        assertThat(this.retrieve()).containsOnly(
                this.parisDTO,
                this.londonDTO
        );
    }

    private Set<FlightDTO> retrieve() {
        return new RetrieveFlights(this.flightRepository).execute();
    }
}
