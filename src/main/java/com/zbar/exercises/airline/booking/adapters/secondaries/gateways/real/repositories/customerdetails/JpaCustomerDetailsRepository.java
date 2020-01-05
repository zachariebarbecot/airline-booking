package com.zbar.exercises.airline.booking.adapters.secondaries.gateways.real.repositories.customerdetails;

import com.zbar.exercises.airline.booking.core.entities.CustomerDetails;
import com.zbar.exercises.airline.booking.core.gateways.repositories.CustomerDetailsRepository;
import lombok.AllArgsConstructor;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@AllArgsConstructor
public class JpaCustomerDetailsRepository implements CustomerDetailsRepository {

    private final SpringJpaCustomerDetailsRepository repository;


    @Override
    public void save(CustomerDetails customerDetails) {
        this.saveAll(Collections.singleton(customerDetails));
    }

    @Override
    public void saveAll(Set<CustomerDetails> customerDetails) {
        this.repository.saveAll(
                customerDetails.stream()
                        .map(CustomerDetailsMapper::toEntity)
                        .collect(Collectors.toSet())
        );
    }

    @Override
    public Optional<CustomerDetails> byCustomerId(String customerId) {
        return this.repository.findByCustomerNumber(customerId)
                .map(CustomerDetailsMapper::toDomain);
    }
}
