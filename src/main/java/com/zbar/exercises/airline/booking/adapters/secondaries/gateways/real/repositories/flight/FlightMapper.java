package com.zbar.exercises.airline.booking.adapters.secondaries.gateways.real.repositories.flight;

import com.zbar.exercises.airline.booking.adapters.secondaries.gateways.real.entities.FlightEntity;
import com.zbar.exercises.airline.booking.core.entities.Country;
import com.zbar.exercises.airline.booking.core.entities.Flight;
import com.zbar.exercises.airline.booking.core.entities.FlightId;

public class FlightMapper {

    public static Flight toDomain(FlightEntity entity) {
        var flight = new Flight(
                new FlightId(entity.getNumber()),
                Country.valueOf(entity.getFlyingFrom()),
                Country.valueOf(entity.getFlyingTo()),
                entity.getDeparting(),
                entity.getReturning(),
                entity.getMaxNumberPassengers()
        );
        flight.identity(entity.getId());
        return flight;
    }

    public static FlightEntity toEntity(Flight domain) {
        return new FlightEntity(
                domain.identity(),
                domain.getFlightIdValue(),
                domain.getFlyingFrom().name(),
                domain.getFlyingTo().name(),
                domain.getDeparting(),
                domain.getReturning(),
                domain.getMaxNumberPassengers()
        );
    }
}
