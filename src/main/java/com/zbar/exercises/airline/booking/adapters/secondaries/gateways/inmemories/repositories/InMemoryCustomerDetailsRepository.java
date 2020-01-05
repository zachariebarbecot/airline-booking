package com.zbar.exercises.airline.booking.adapters.secondaries.gateways.inmemories.repositories;

import com.zbar.exercises.airline.booking.core.entities.CustomerDetails;
import com.zbar.exercises.airline.booking.core.gateways.repositories.CustomerDetailsRepository;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

public class InMemoryCustomerDetailsRepository implements CustomerDetailsRepository {

    private Set<CustomerDetails> details = new LinkedHashSet<>();

    @Override
    public void save(CustomerDetails details) {
        this.saveAll(Collections.singleton(details));
    }

    @Override
    public void saveAll(Set<CustomerDetails> details) {
        this.details.addAll(details);
    }

    @Override
    public Optional<CustomerDetails> byCustomerId(String customerId) {
        return this.details.stream()
                .filter(details -> details.getCustomerIdValue().equals(customerId))
                .findFirst();
    }
}
