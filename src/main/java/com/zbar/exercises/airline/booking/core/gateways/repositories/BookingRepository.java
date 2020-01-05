package com.zbar.exercises.airline.booking.core.gateways.repositories;

import com.zbar.exercises.airline.booking.core.entities.Booking;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

public interface BookingRepository {

    void save(Booking booking);

    void saveAll(Set<Booking> bookings);

    Set<Booking> all();

    void removeAll();

    Set<Booking> allByFlightId(String flightId);

    Set<Booking> allBookedOn(LocalDateTime bookingOn);

    Optional<Booking> ByCustomerIdAndFlightId(String customerId, String flightId);
}
