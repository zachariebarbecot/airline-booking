package com.zbar.exercises.airline.booking.core.gateways.repositories;

import com.zbar.exercises.airline.booking.core.entities.Flight;

import java.util.Optional;
import java.util.Set;

public interface FlightRepository {

    void save(Flight flight);

    void saveAll(Set<Flight> flights);

    Set<Flight> all();

    Set<Flight> allFromFlyingFromAndFlyingTo(String flyingFrom, String flyingTo);

    void removeAll();

    Optional<Flight> byFlightId(String flightId);
}
