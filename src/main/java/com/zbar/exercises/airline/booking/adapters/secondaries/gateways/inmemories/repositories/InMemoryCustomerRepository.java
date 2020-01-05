package com.zbar.exercises.airline.booking.adapters.secondaries.gateways.inmemories.repositories;

import com.zbar.exercises.airline.booking.core.entities.Customer;
import com.zbar.exercises.airline.booking.core.gateways.repositories.CustomerRepository;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

public class InMemoryCustomerRepository implements CustomerRepository {

    private Set<Customer> customers = new LinkedHashSet<>();

    @Override
    public void save(Customer customer) {
        this.customers.addAll(Collections.singleton(customer));
    }

    @Override
    public void saveAll(Set<Customer> customers) {
        this.customers.addAll(customers);
    }

    @Override
    public Set<Customer> all() {
        return Collections.unmodifiableSet(this.customers);
    }

    @Override
    public void removeAll() {
        this.customers.clear();
    }

    @Override
    public Optional<Customer> byCustomerId(String customerId) {
        return this.customers.stream()
                .filter(customer -> customer.getCustomerId().getId().equals(customerId))
                .findFirst();
    }

    @Override
    public Optional<Customer> byUsername(String username) {
        return this.customers.stream()
                .filter(customer -> customer.getUsername().equals(username))
                .findFirst();
    }
}
