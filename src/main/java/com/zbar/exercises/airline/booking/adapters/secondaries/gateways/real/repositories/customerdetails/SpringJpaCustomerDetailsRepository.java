package com.zbar.exercises.airline.booking.adapters.secondaries.gateways.real.repositories.customerdetails;

import com.zbar.exercises.airline.booking.adapters.secondaries.gateways.real.entities.CustomerDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringJpaCustomerDetailsRepository extends JpaRepository<CustomerDetailsEntity, Long> {

    public Optional<CustomerDetailsEntity> findByCustomerNumber(String number);
}
