package com.zbar.exercises.airline.booking.adapters.secondaries.gateways.inmemories.repositories;

import com.zbar.exercises.airline.booking.core.entities.Flight;
import com.zbar.exercises.airline.booking.core.gateways.repositories.FlightRepository;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class InMemoryFlightRepository implements FlightRepository {

    private Set<Flight> flights = new LinkedHashSet<>();

    @Override
    public void save(Flight flight) {
        this.saveAll(Collections.singleton(flight));
    }

    @Override
    public void saveAll(Set<Flight> flights) {
        this.flights.addAll(flights);
    }

    @Override
    public Set<Flight> all() {
        return Collections.unmodifiableSet(this.flights);
    }

    @Override
    public Set<Flight> allFromFlyingFromAndFlyingTo(String flyingFrom, String flyingTo) {
        return this.flights.stream()
                .filter(flight -> this.isFlyingFromAndFlyingToDateAreTheSame(flyingFrom, flyingTo, flight))
                .collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public void removeAll() {
        this.flights.clear();
    }

    @Override
    public Optional<Flight> byFlightId(String flightId) {
        return this.flights.stream()
                .filter(travel -> travel.getFlightId().getId().equals(flightId))
                .findFirst();
    }

    private boolean isFlyingFromAndFlyingToDateAreTheSame(String flyingFrom, String flyingTo, Flight flight) {
        return flight.getFlyingFrom().getValue().equals(flyingFrom) &&
                flight.getFlyingTo().getValue().equals(flyingTo);
    }
}
