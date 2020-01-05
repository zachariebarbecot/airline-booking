package com.zbar.exercises.airline.booking.core.usecases.flights.retrieval;

import com.zbar.exercises.airline.booking.core.entities.Country;
import com.zbar.exercises.airline.booking.core.entities.FlightId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FlightDTO {

    private FlightId flightId;
    private Country flyingFrom;
    private Country flyingTo;
    private LocalDateTime departing;
    private LocalDateTime returning;
    private Integer maxNumberPassengers;

    public FlightDTO(FlightId flightId, Country flyingFrom, Country flyingTo, LocalDateTime departing,
                     LocalDateTime returning, Integer maxNumberPassengers) {
        this.flightId = flightId;
        this.flyingFrom = flyingFrom;
        this.flyingTo = flyingTo;
        this.departing = departing;
        this.returning = returning;
        this.maxNumberPassengers = maxNumberPassengers;
    }
}
