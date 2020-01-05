package com.zbar.exercises.airline.booking.core.gateways.repositories;

import com.zbar.exercises.airline.booking.core.entities.CustomerDetails;

import java.util.Optional;
import java.util.Set;

public interface CustomerDetailsRepository {

    void save(CustomerDetails customerDetails);

    void saveAll(Set<CustomerDetails> customerDetails);

    Optional<CustomerDetails> byCustomerId(String customerId);
}
