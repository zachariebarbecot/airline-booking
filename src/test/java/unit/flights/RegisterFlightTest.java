package unit.flights;

import com.zbar.exercises.airline.booking.adapters.secondaries.gateways.inmemories.ports.InMemoryIdGeneratorPort;
import com.zbar.exercises.airline.booking.adapters.secondaries.gateways.inmemories.repositories.InMemoryFlightRepository;
import com.zbar.exercises.airline.booking.core.entities.Country;
import com.zbar.exercises.airline.booking.core.entities.Flight;
import com.zbar.exercises.airline.booking.core.entities.FlightId;
import com.zbar.exercises.airline.booking.core.exceptions.AlreadyExistsException;
import com.zbar.exercises.airline.booking.core.exceptions.ComparedException;
import com.zbar.exercises.airline.booking.core.exceptions.RequiredParameterException;
import com.zbar.exercises.airline.booking.core.gateways.ports.IdGeneratorPort;
import com.zbar.exercises.airline.booking.core.gateways.repositories.FlightRepository;
import com.zbar.exercises.airline.booking.core.usecases.flights.registration.FlightDTO;
import com.zbar.exercises.airline.booking.core.usecases.flights.registration.RegisterFlight;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

public class RegisterFlightTest {

    private final FlightRepository flightRepository = new InMemoryFlightRepository();
    private final IdGeneratorPort idGeneratorPort = new InMemoryIdGeneratorPort();

    @Test(expected = RequiredParameterException.class)
    public void shouldNotRegisterBecauseFlyingFromIsNull() {
        var dto = new FlightDTO(
                null,
                Country.EN,
                LocalDateTime.of(2020, Month.JANUARY, 1, 0, 0),
                LocalDateTime.of(2020, Month.JANUARY, 2, 0, 0),
                250
        );
        this.register(dto);
    }

    @Test(expected = RequiredParameterException.class)
    public void shouldNotRegisterBecauseFlyingToIsNull() {
        var dto = new FlightDTO(
                Country.FR,
                null,
                LocalDateTime.of(2020, Month.JANUARY, 1, 0, 0),
                LocalDateTime.of(2020, Month.JANUARY, 2, 0, 0),
                250
        );
        this.register(dto);
    }

    @Test(expected = RequiredParameterException.class)
    public void shouldNotRegisterBecauseDepartingIsNull() {
        var dto = new FlightDTO(
                Country.FR,
                Country.EN,
                null,
                LocalDateTime.of(2020, Month.JANUARY, 2, 0, 0),
                250
        );
        this.register(dto);
    }

    @Test(expected = RequiredParameterException.class)
    public void shouldNotRegisterBecauseReturningIsNull() {
        var dto = new FlightDTO(
                Country.FR,
                Country.EN,
                LocalDateTime.of(2020, Month.JANUARY, 1, 0, 0),
                null,
                250
        );
        this.register(dto);
    }

    @Test(expected = ComparedException.class)
    public void shouldNotRegisterBecauseFlyingFromAndFlyingToAreEqual() {
        var dto = new FlightDTO(
                Country.FR,
                Country.FR,
                LocalDateTime.of(2020, Month.JANUARY, 1, 0, 0),
                LocalDateTime.of(2020, Month.JANUARY, 2, 0, 0),
                250
        );
        this.register(dto);
    }

    @Test(expected = ComparedException.class)
    public void shouldNotRegisterBecauseDepartingIsAfterReturningDate() {
        var dto = new FlightDTO(
                Country.FR,
                Country.EN,
                LocalDateTime.of(2020, Month.JANUARY, 2, 0, 0),
                LocalDateTime.of(2020, Month.JANUARY, 1, 0, 0),
                250
        );
        this.register(dto);
    }

    @Test(expected = AlreadyExistsException.class)
    public void shouldNotRegisterBecauseFlightAlreadyExists() {
        var flight = new Flight(
                null,
                Country.FR,
                Country.EN,
                LocalDateTime.of(2020, Month.JANUARY, 1, 0, 0),
                LocalDateTime.of(2020, Month.JANUARY, 2, 0, 0),
                250
        );
        var dto = new FlightDTO(
                Country.FR,
                Country.EN,
                LocalDateTime.of(2020, Month.JANUARY, 1, 0, 0),
                LocalDateTime.of(2020, Month.JANUARY, 2, 0, 0),
                250
        );
        this.flightRepository.save(flight);
        this.register(dto);
    }

    @Test
    public void shouldRegisterFlight() {
        var dto = new FlightDTO(
                Country.FR,
                Country.EN,
                LocalDateTime.of(2020, Month.JANUARY, 1, 0, 0),
                LocalDateTime.of(2020, Month.JANUARY, 2, 0, 0),
                250
        );
        this.register(dto);
        assertThat(this.flightRepository
                .allFromFlyingFromAndFlyingTo(dto.getFlyingFrom().getValue(), dto.getFlyingTo().getValue()))
                .isNotEmpty()
                .containsOnly(new Flight(
                        new FlightId("123-456-789"),
                        dto.getFlyingFrom(),
                        dto.getFlyingTo(),
                        dto.getDeparting(),
                        dto.getReturning(),
                        dto.getMaxNumberPassengers()
                ));
    }

    private void register(FlightDTO dto) {
        new RegisterFlight(this.flightRepository, this.idGeneratorPort)
                .execute(dto);
    }
}
