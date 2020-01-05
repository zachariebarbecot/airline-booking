package com.zbar.exercises.airline.booking.adapters.secondaries.gateways.real.repositories.flight;

import com.zbar.exercises.airline.booking.core.entities.Flight;
import com.zbar.exercises.airline.booking.core.gateways.repositories.FlightRepository;
import lombok.AllArgsConstructor;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@AllArgsConstructor
public class JpaFlightRepository implements FlightRepository {

    private final SpringJpaFlightRepository repository;

    @Override
    public void save(Flight flight) {
        this.saveAll(Collections.singleton(flight));
    }

    @Override
    public void saveAll(Set<Flight> flights) {
        this.repository.saveAll(
                flights.stream()
                        .map(FlightMapper::toEntity)
                        .collect(Collectors.toSet())
        );
    }

    @Override
    public Set<Flight> all() {
        return this.repository.findAll().stream()
                .map(FlightMapper::toDomain)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Flight> allFromFlyingFromAndFlyingTo(String flyingFrom, String flyingTo) {
        return this.repository.findAllByFlyingFromAndFlyingTo(flyingFrom, flyingTo).stream()
                .map(FlightMapper::toDomain)
                .collect(Collectors.toSet());
    }

    @Override
    public void removeAll() {
        this.repository.deleteAll();
    }

    @Override
    public Optional<Flight> byFlightId(String flightId) {
        return this.repository.findByNumber(flightId)
                .map(FlightMapper::toDomain);
    }
}
