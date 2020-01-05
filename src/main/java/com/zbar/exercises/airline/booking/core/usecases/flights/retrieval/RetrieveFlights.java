package com.zbar.exercises.airline.booking.core.usecases.flights.retrieval;

import com.zbar.exercises.airline.booking.core.entities.Flight;
import com.zbar.exercises.airline.booking.core.gateways.repositories.FlightRepository;
import lombok.AllArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class RetrieveFlights {

    private final FlightRepository flightRepository;

    public Set<FlightDTO> execute() {
        return this.flightRepository.all()
                .stream()
                .map(this::buildDto)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private FlightDTO buildDto(Flight flight) {
        return new FlightDTO(
                flight.getFlightId(),
                flight.getFlyingFrom(),
                flight.getFlyingTo(),
                flight.getDeparting(),
                flight.getReturning(),
                flight.getMaxNumberPassengers()
        );
    }
}
