package com.zbar.exercises.airline.booking.core.entities;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class Booking extends Entity {

    private BookingId bookingId;
    private Customer customer;
    private Flight flight;
    private LocalDateTime bookingOn;

    public String getBookingIdValue() {
        return this.bookingId.getId();
    }
}
