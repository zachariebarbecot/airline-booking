package com.zbar.exercises.airline.booking.adapters.secondaries.gateways.real.repositories.flight;

import com.zbar.exercises.airline.booking.adapters.secondaries.gateways.real.entities.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface SpringJpaFlightRepository extends JpaRepository<FlightEntity, Long> {

    Set<FlightEntity> findAllByFlyingFromAndFlyingTo(String flyingFrom, String flyingTo);

    Optional<FlightEntity> findByNumber(String number);
}
