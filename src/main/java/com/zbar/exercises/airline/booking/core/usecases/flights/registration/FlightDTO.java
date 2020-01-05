package com.zbar.exercises.airline.booking.core.usecases.flights.registration;

import com.zbar.exercises.airline.booking.core.entities.Country;
import com.zbar.exercises.airline.booking.core.exceptions.ComparedException;
import com.zbar.exercises.airline.booking.core.exceptions.RequiredParameterException;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FlightDTO {

    private Country flyingFrom;
    private Country flyingTo;
    private LocalDateTime departing;
    private LocalDateTime returning;
    private Integer maxNumberPassengers;

    public FlightDTO(Country flyingFrom, Country flyingTo, LocalDateTime departing,
                     LocalDateTime returning, Integer maxNumberPassengers) {
        if (flyingFrom == null) {
            throw new RequiredParameterException("flyingFrom is required");
        }
        if (flyingTo == null) {
            throw new RequiredParameterException("flyingTo is required");
        }
        if (departing == null) {
            throw new RequiredParameterException("departing is required");
        }
        if (returning == null) {
            throw new RequiredParameterException("returning is required");
        }
        if (flyingFrom.equals(flyingTo)) {
            throw new ComparedException("flyingFrom must be different from flyingTo");
        }
        if (departing.isAfter(returning)) {
            throw new ComparedException("departing must be before returning date");
        }
        this.flyingFrom = flyingFrom;
        this.flyingTo = flyingTo;
        this.departing = departing;
        this.returning = returning;
        this.maxNumberPassengers = maxNumberPassengers;
    }
}
