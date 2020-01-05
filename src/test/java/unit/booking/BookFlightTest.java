package unit.booking;

import com.zbar.exercises.airline.booking.adapters.secondaries.gateways.inmemories.ports.InMemoryAuthenticationPort;
import com.zbar.exercises.airline.booking.adapters.secondaries.gateways.inmemories.ports.InMemoryIdGeneratorPort;
import com.zbar.exercises.airline.booking.adapters.secondaries.gateways.inmemories.repositories.InMemoryBookingRepository;
import com.zbar.exercises.airline.booking.adapters.secondaries.gateways.inmemories.repositories.InMemoryFlightRepository;
import com.zbar.exercises.airline.booking.core.entities.*;
import com.zbar.exercises.airline.booking.core.exceptions.AlreadyExistsException;
import com.zbar.exercises.airline.booking.core.exceptions.FulledFlightException;
import com.zbar.exercises.airline.booking.core.exceptions.UnauthorizedAccessException;
import com.zbar.exercises.airline.booking.core.exceptions.UnknownException;
import com.zbar.exercises.airline.booking.core.gateways.ports.AuthenticationPort;
import com.zbar.exercises.airline.booking.core.gateways.ports.IdGeneratorPort;
import com.zbar.exercises.airline.booking.core.gateways.repositories.BookingRepository;
import com.zbar.exercises.airline.booking.core.gateways.repositories.FlightRepository;
import com.zbar.exercises.airline.booking.core.usecases.booking.BookFlight;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

@AllArgsConstructor
public class BookFlightTest {

    private final BookingRepository bookingRepository = new InMemoryBookingRepository();
    private final FlightRepository flightRepository = new InMemoryFlightRepository();
    private final AuthenticationPort authenticationPort = new InMemoryAuthenticationPort();
    private final IdGeneratorPort idGeneratorPort = new InMemoryIdGeneratorPort();

    @Test(expected = UnauthorizedAccessException.class)
    public void shouldNotBookBecauseNotAuthenticated() {
        this.bookFlight(new FlightId("123-456-789"));
    }

    @Test(expected = AlreadyExistsException.class)
    public void shouldNotBookBecauseAlreadyExists() {
        var customer = new Customer(
                new CustomerId(this.idGeneratorPort.generate()),
                "zbarbecot",
                "encodedpassword"
        );
        var flight = new Flight(
                new FlightId("123-456-789"),
                Country.FR,
                Country.EN,
                LocalDateTime.of(2020, Month.JANUARY, 1, 0, 0),
                LocalDateTime.of(2020, Month.JANUARY, 2, 0, 0),
                250
        );
        var booking = new Booking(
                new BookingId("123-456-789"),
                customer,
                flight,
                LocalDateTime.of(2020, Month.JANUARY, 1, 0, 0)
        );
        this.authenticationPort.authenticate(customer);
        this.flightRepository.save(flight);
        this.bookingRepository.save(booking);
        this.bookFlight(new FlightId("123-456-789"));
    }

    @Test(expected = UnknownException.class)
    public void shouldNotBookBecauseFlightNotExists() {
        var customer = new Customer(
                new CustomerId(this.idGeneratorPort.generate()),
                "zbarbecot",
                "encodedpassword"
        );
        this.authenticationPort.authenticate(customer);
        this.bookFlight(new FlightId("123-456-789"));
    }

    @Test(expected = FulledFlightException.class)
    public void shouldNotBookBecauseFlightHadNotVacantPlaces() {
        var customer = new Customer(
                new CustomerId(this.idGeneratorPort.generate()),
                "zbarbecot",
                "encodedpassword"
        );
        var flight = new Flight(
                new FlightId("123-456-789"),
                Country.FR,
                Country.EN,
                LocalDateTime.of(2020, Month.JANUARY, 1, 0, 0),
                LocalDateTime.of(2020, Month.JANUARY, 2, 0, 0),
                0
        );
        this.authenticationPort.authenticate(customer);
        this.flightRepository.save(flight);
        this.bookFlight(new FlightId("123-456-789"));
    }


    // TODO refacto to add assert for LocalDateTime
    @Test
    public void shouldBookFlight() {
        var customer = new Customer(
                new CustomerId(this.idGeneratorPort.generate()),
                "zbarbecot",
                "encodedpassword"
        );
        var flight = new Flight(
                new FlightId("123-456-789"),
                Country.FR,
                Country.EN,
                LocalDateTime.of(2020, Month.JANUARY, 1, 0, 0),
                LocalDateTime.of(2020, Month.JANUARY, 2, 0, 0),
                1
        );
        this.authenticationPort.authenticate(customer);
        this.flightRepository.save(flight);
        this.bookFlight(new FlightId("123-456-789"));
    }

    private void bookFlight(FlightId flightId) {
        new BookFlight(bookingRepository, flightRepository, authenticationPort, idGeneratorPort)
                .execute(flightId);
    }
}
