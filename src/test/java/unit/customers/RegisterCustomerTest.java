package unit.customers;

import com.zbar.exercises.airline.booking.adapters.secondaries.gateways.inmemories.ports.InMemoryIdGeneratorPort;
import com.zbar.exercises.airline.booking.adapters.secondaries.gateways.inmemories.ports.InMemoryPasswordEncoderPort;
import com.zbar.exercises.airline.booking.adapters.secondaries.gateways.inmemories.repositories.InMemoryCustomerDetailsRepository;
import com.zbar.exercises.airline.booking.adapters.secondaries.gateways.inmemories.repositories.InMemoryCustomerRepository;
import com.zbar.exercises.airline.booking.core.entities.Country;
import com.zbar.exercises.airline.booking.core.entities.Customer;
import com.zbar.exercises.airline.booking.core.entities.CustomerDetails;
import com.zbar.exercises.airline.booking.core.exceptions.AlreadyExistsException;
import com.zbar.exercises.airline.booking.core.exceptions.RequiredParameterException;
import com.zbar.exercises.airline.booking.core.gateways.ports.IdGeneratorPort;
import com.zbar.exercises.airline.booking.core.gateways.ports.PasswordEncoderPort;
import com.zbar.exercises.airline.booking.core.gateways.repositories.CustomerDetailsRepository;
import com.zbar.exercises.airline.booking.core.gateways.repositories.CustomerRepository;
import com.zbar.exercises.airline.booking.core.usecases.customers.registration.CustomerDTO;
import com.zbar.exercises.airline.booking.core.usecases.customers.registration.CustomerDetailsDTO;
import com.zbar.exercises.airline.booking.core.usecases.customers.registration.RegisterCustomer;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RegisterCustomerTest {

    private final CustomerRepository customerRepository = new InMemoryCustomerRepository();
    private final CustomerDetailsRepository customerDetailsRepository = new InMemoryCustomerDetailsRepository();
    private final IdGeneratorPort idGeneratorPort = new InMemoryIdGeneratorPort();
    private final PasswordEncoderPort passwordEncoderPort = new InMemoryPasswordEncoderPort();

    @Test(expected = RequiredParameterException.class)
    public void shouldNotRegisterBecauseUsernameIsNull() {
        var dto = new CustomerDTO(null, "password");
        this.register(dto, null);
    }

    @Test(expected = RequiredParameterException.class)
    public void shouldNotRegisterBecausePasswordIsNull() {
        var dto = new CustomerDTO("zbarbecot", null);
        this.register(dto, null);
    }

    @Test(expected = RequiredParameterException.class)
    public void shouldNotRegisterBecauseFirstNameIsNull() {
        var customerDTO = new CustomerDTO("zbarbecot", "password");
        var customerDetailsDTO = new CustomerDetailsDTO(null, "barbecot", Country.FR);
        this.register(customerDTO, customerDetailsDTO);
    }

    @Test(expected = RequiredParameterException.class)
    public void shouldNotRegisterBecauseLastNameIsNull() {
        var customerDTO = new CustomerDTO("zbarbecot", "password");
        var customerDetailsDTO = new CustomerDetailsDTO("zacharie", null, Country.FR);
        this.register(customerDTO, customerDetailsDTO);
    }

    @Test(expected = RequiredParameterException.class)
    public void shouldNotRegisterBecauseCountryIsNull() {
        var customerDTO = new CustomerDTO("zbarbecot", "password");
        var customerDetailsDTO = new CustomerDetailsDTO("zacharie", "barbecot", null);
        this.register(customerDTO, customerDetailsDTO);
    }

    @Test(expected = AlreadyExistsException.class)
    public void shouldNotRegisterBecauseCustomerAlreadyExists() {
        var customer = new Customer(null, "zbarbecot", "encodedpassword");
        var customerDTO = new CustomerDTO("zbarbecot", "password");
        var customerDetailsDTO = new CustomerDetailsDTO("zacharie", "barbecot", Country.FR);
        this.customerRepository.save(customer);
        this.register(customerDTO, customerDetailsDTO);
    }

    @Test
    public void shouldRegisterCustomer() {
        var customerDTO = new CustomerDTO("zbarbecot", "password");
        var customerDetailsDTO = new CustomerDetailsDTO("Zacharie", "BARBECOT", Country.FR);
        this.register(customerDTO, customerDetailsDTO);
        assertThat(this.customerRepository.byUsername(customerDTO.getUsername()))
                .isNotEmpty()
                .hasValueSatisfying(user -> assertUser(customerDTO, user))
                .map(customer ->
                        assertThat(this.customerDetailsRepository.byCustomerId(customer.getCustomerIdValue()))
                                .isNotEmpty()
                                .hasValueSatisfying(details -> {
                                    assertDetails(customerDetailsDTO, details);
                                })
                );
    }

    private void register(CustomerDTO customerDTO, CustomerDetailsDTO detailsDTO) {
        new RegisterCustomer(this.customerRepository, this.customerDetailsRepository,
                this.idGeneratorPort, this.passwordEncoderPort)
                .execute(customerDTO, detailsDTO);
    }

    private void assertUser(CustomerDTO dto, Customer customer) {
        assertThat(customer.getCustomerId()).isNotNull();
        assertThat(customer.getCustomerIdValue()).isEqualTo("123-456-789");
        assertThat(customer.getUsername()).isEqualTo(dto.getUsername());
        assertThat(customer.getPassword()).isEqualTo("encoded" + dto.getPassword());
    }

    private void assertDetails(CustomerDetailsDTO dto, CustomerDetails details) {
        assertThat(details.getCustomer()).isNotNull();
        assertThat(details.getCustomerIdValue()).isEqualTo("123-456-789");
        assertThat(details.getFirstName()).isEqualTo(dto.getFirstName());
        assertThat(details.getLastName()).isEqualTo(dto.getLastName());
        assertThat(details.getCountry()).isEqualTo(dto.getCountry());
    }
}
