package com.zbar.exercises.airline.booking.core.usecases.customers.registration;

import com.zbar.exercises.airline.booking.core.entities.Customer;
import com.zbar.exercises.airline.booking.core.entities.CustomerDetails;
import com.zbar.exercises.airline.booking.core.entities.CustomerId;
import com.zbar.exercises.airline.booking.core.exceptions.AlreadyExistsException;
import com.zbar.exercises.airline.booking.core.gateways.ports.IdGeneratorPort;
import com.zbar.exercises.airline.booking.core.gateways.ports.PasswordEncoderPort;
import com.zbar.exercises.airline.booking.core.gateways.repositories.CustomerDetailsRepository;
import com.zbar.exercises.airline.booking.core.gateways.repositories.CustomerRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegisterCustomer {

    private final CustomerRepository customerRepository;
    private final CustomerDetailsRepository customerDetailsRepository;
    private final IdGeneratorPort idGeneratorPort;
    private final PasswordEncoderPort passwordEncoderPort;

    public void execute(CustomerDTO customerDTO, CustomerDetailsDTO detailsDTO) {
        this.customerRepository.byUsername(customerDTO.getUsername())
                .ifPresent(customer -> {
                    throw new AlreadyExistsException(customer.getUsername() + " already exists");
                });
        this.customerRepository.save(this.buildNewCustomer(customerDTO));
        this.customerRepository.byUsername(customerDTO.getUsername())
                .ifPresent(customer -> this.customerDetailsRepository.save(this.buildNewDetails(detailsDTO, customer)));

    }

    private Customer buildNewCustomer(CustomerDTO dto) {
        return new Customer(
                new CustomerId(this.idGeneratorPort.generate()),
                dto.getUsername(),
                this.passwordEncoderPort.encode(dto.getPassword())
        );
    }

    private CustomerDetails buildNewDetails(CustomerDetailsDTO dto, Customer customer) {
        return new CustomerDetails(
                customer,
                dto.getFirstName(),
                dto.getLastName(),
                dto.getCountry()
        );
    }
}
