package com.zbar.exercises.airline.booking.adapters.secondaries.gateways.inmemories.ports;

import com.zbar.exercises.airline.booking.core.entities.Customer;
import com.zbar.exercises.airline.booking.core.gateways.ports.AuthenticationPort;

import java.util.Optional;

import static java.util.Optional.ofNullable;

public class InMemoryAuthenticationPort implements AuthenticationPort {

    Customer customer;

    @Override
    public void authenticate(Customer customer) {
        this.customer = customer;
    }

    @Override
    public Optional<Customer> currentCustomer() {
        return ofNullable(this.customer);
    }
}
