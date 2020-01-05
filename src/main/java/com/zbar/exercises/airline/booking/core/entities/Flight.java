package com.zbar.exercises.airline.booking.core.entities;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class Flight extends Entity {

    private FlightId flightId;
    private Country flyingFrom;
    private Country flyingTo;
    private LocalDateTime departing;
    private LocalDateTime returning;
    private Integer maxNumberPassengers;

    public String getFlightIdValue() {
        return this.flightId.getId();
    }
}
