package com.zbar.exercises.airline.booking.core.usecases.flights.registration;

import com.zbar.exercises.airline.booking.core.entities.Flight;
import com.zbar.exercises.airline.booking.core.entities.FlightId;
import com.zbar.exercises.airline.booking.core.exceptions.AlreadyExistsException;
import com.zbar.exercises.airline.booking.core.gateways.ports.IdGeneratorPort;
import com.zbar.exercises.airline.booking.core.gateways.repositories.FlightRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegisterFlight {

    private final FlightRepository flightRepository;
    private final IdGeneratorPort idGeneratorPort;

    public void execute(FlightDTO dto) {
        this.flightRepository
                .allFromFlyingFromAndFlyingTo(dto.getFlyingFrom().getValue(), dto.getFlyingTo().getValue())
                .stream()
                .filter(flight -> this.isDepartingAndReturningAreEqual(dto, flight))
                .findAny()
                .ifPresent(flight -> {
                    throw new AlreadyExistsException("Flight from " + flight.getFlyingFrom() + " to " +
                            flight.getFlyingTo() + " for departing " + flight.getDeparting() +
                            " and returning " + flight.getReturning() + " already exists");
                });
        this.flightRepository.save(this.buildNewFlight(dto));
    }

    private boolean isDepartingAndReturningAreEqual(FlightDTO dto, Flight flight) {
        return dto.getDeparting().equals(flight.getDeparting())
                && dto.getReturning().equals(flight.getReturning());
    }

    private Flight buildNewFlight(FlightDTO dto) {
        return new Flight(
                new FlightId(this.idGeneratorPort.generate()),
                dto.getFlyingFrom(),
                dto.getFlyingTo(),
                dto.getDeparting(),
                dto.getReturning(),
                dto.getMaxNumberPassengers()
        );
    }
}
