package com.zbar.exercises.airline.booking.adapters.secondaries.gateways.real.repositories.customer;

import com.zbar.exercises.airline.booking.core.entities.Customer;
import com.zbar.exercises.airline.booking.core.gateways.repositories.CustomerRepository;
import lombok.AllArgsConstructor;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@AllArgsConstructor
public class JpaCustomerRepository implements CustomerRepository {

    private final SpringJpaCustomerRepository repository;

    @Override
    public void save(Customer customer) {
        this.saveAll(Collections.singleton(customer));
    }

    @Override
    public void saveAll(Set<Customer> customers) {
        this.repository.saveAll(
                customers.stream()
                        .map(CustomerMapper::toEntity)
                        .collect(Collectors.toSet())
        );
    }

    @Override
    public Set<Customer> all() {
        return this.repository.findAll().stream()
                .map(CustomerMapper::toDomain)
                .collect(Collectors.toSet());
    }

    @Override
    public void removeAll() {
        this.repository.deleteAll();
    }

    @Override
    public Optional<Customer> byCustomerId(String customerId) {
        return this.repository.findByNumber(customerId)
                .map(CustomerMapper::toDomain);
    }

    @Override
    public Optional<Customer> byUsername(String username) {
        return this.repository.findByUsername(username)
                .map(CustomerMapper::toDomain);
    }
}
