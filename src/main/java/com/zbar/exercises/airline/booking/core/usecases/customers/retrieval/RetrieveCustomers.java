package com.zbar.exercises.airline.booking.core.usecases.customers.retrieval;

import com.zbar.exercises.airline.booking.core.entities.Country;
import com.zbar.exercises.airline.booking.core.entities.Customer;
import com.zbar.exercises.airline.booking.core.entities.CustomerDetails;
import com.zbar.exercises.airline.booking.core.gateways.repositories.CustomerDetailsRepository;
import com.zbar.exercises.airline.booking.core.gateways.repositories.CustomerRepository;
import lombok.AllArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class RetrieveCustomers {

    private final CustomerRepository customerRepository;
    private final CustomerDetailsRepository customerDetailsRepository;

    public Set<CustomerDTO> execute() {
        return this.customerRepository.all()
                .stream()
                .map(this::buildCustomerDto)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private CustomerDTO buildCustomerDto(Customer customer) {
        var details = this.customerDetailsRepository.byCustomerId(customer.getCustomerIdValue())
                .orElseGet(() -> new CustomerDetails(customer, "", "", Country.UKN));
        return new CustomerDTO(
                customer.getCustomerId(),
                customer.getUsername(),
                this.buildDetailsDto(details)
        );
    }

    private CustomerDetailsDTO buildDetailsDto(CustomerDetails details) {
        return new CustomerDetailsDTO(
                details.getFirstName(),
                details.getLastName(),
                details.getCountry()
        );
    }
}
