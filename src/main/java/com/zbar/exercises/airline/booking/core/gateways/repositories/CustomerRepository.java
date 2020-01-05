package com.zbar.exercises.airline.booking.core.gateways.repositories;

import com.zbar.exercises.airline.booking.core.entities.Customer;

import java.util.Optional;
import java.util.Set;

public interface CustomerRepository {

    void save(Customer customer);

    void saveAll(Set<Customer> customers);

    Set<Customer> all();

    void removeAll();

    Optional<Customer> byCustomerId(String customerId);

    Optional<Customer> byUsername(String username);
}
