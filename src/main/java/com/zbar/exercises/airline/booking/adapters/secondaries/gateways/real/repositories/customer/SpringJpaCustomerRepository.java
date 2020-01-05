package com.zbar.exercises.airline.booking.adapters.secondaries.gateways.real.repositories.customer;

import com.zbar.exercises.airline.booking.adapters.secondaries.gateways.real.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringJpaCustomerRepository extends JpaRepository<CustomerEntity, Long> {

    Optional<CustomerEntity> findByNumber(String number);

    Optional<CustomerEntity> findByUsername(String username);
}
