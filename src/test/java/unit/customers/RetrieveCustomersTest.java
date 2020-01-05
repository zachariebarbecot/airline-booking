package unit.customers;

import com.zbar.exercises.airline.booking.adapters.secondaries.gateways.inmemories.repositories.InMemoryCustomerDetailsRepository;
import com.zbar.exercises.airline.booking.adapters.secondaries.gateways.inmemories.repositories.InMemoryCustomerRepository;
import com.zbar.exercises.airline.booking.core.entities.Country;
import com.zbar.exercises.airline.booking.core.entities.Customer;
import com.zbar.exercises.airline.booking.core.entities.CustomerId;
import com.zbar.exercises.airline.booking.core.gateways.repositories.CustomerDetailsRepository;
import com.zbar.exercises.airline.booking.core.gateways.repositories.CustomerRepository;
import com.zbar.exercises.airline.booking.core.usecases.customers.retrieval.CustomerDTO;
import com.zbar.exercises.airline.booking.core.usecases.customers.retrieval.CustomerDetailsDTO;
import com.zbar.exercises.airline.booking.core.usecases.customers.retrieval.RetrieveCustomers;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class RetrieveCustomersTest {

    private final CustomerRepository customerRepository = new InMemoryCustomerRepository();
    private final CustomerDetailsRepository customerDetailsRepository = new InMemoryCustomerDetailsRepository();

    CustomerDetailsDTO zacharieDetailsDTO = new CustomerDetailsDTO("", "", Country.UKN);
    CustomerDTO zacharieDTO = new CustomerDTO(new CustomerId("ZBAR"), "zbarbecot", zacharieDetailsDTO);
    CustomerDetailsDTO johnDetailsDTO = new CustomerDetailsDTO("", "", Country.UKN);
    CustomerDTO johnDTO = new CustomerDTO(new CustomerId("JDOE"), "jdoe", johnDetailsDTO);

    @Test
    public void shouldNotRetrieveAnyUser() {
        assertThat(this.retrieve()).isEmpty();
    }

    @Test
    public void shouldRetrieveAllOfThem() {
        var customers = new HashSet<Customer>();
        var zacharie = new Customer(new CustomerId("ZBAR"), "zbarbecot", "encodedpassword");
        var john = new Customer(new CustomerId("JDOE"), "jdoe", "encodedpassword");
        customers.add(zacharie);
        customers.add(john);
        this.customerRepository.saveAll(customers);
        assertThat(this.retrieve()).containsOnly(
                this.zacharieDTO,
                this.johnDTO
        );
    }

    private Set<CustomerDTO> retrieve() {
        return new RetrieveCustomers(this.customerRepository, this.customerDetailsRepository).execute();
    }
}
