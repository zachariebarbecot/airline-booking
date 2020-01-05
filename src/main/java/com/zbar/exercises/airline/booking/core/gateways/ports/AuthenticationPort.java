package com.zbar.exercises.airline.booking.core.gateways.ports;

import com.zbar.exercises.airline.booking.core.entities.Customer;

import java.util.Optional;

public interface AuthenticationPort {

    void authenticate(Customer customer);

    Optional<Customer> currentCustomer();
}
