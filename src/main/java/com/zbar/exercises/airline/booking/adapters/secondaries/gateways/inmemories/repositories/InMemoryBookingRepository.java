package com.zbar.exercises.airline.booking.adapters.secondaries.gateways.inmemories.repositories;

import com.zbar.exercises.airline.booking.core.entities.Booking;
import com.zbar.exercises.airline.booking.core.gateways.repositories.BookingRepository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class InMemoryBookingRepository implements BookingRepository {

    private Set<Booking> bookings = new LinkedHashSet<>();

    @Override
    public void save(Booking booking) {
        this.saveAll(Collections.singleton(booking));
    }

    @Override
    public void saveAll(Set<Booking> bookings) {
        this.bookings.addAll(bookings);
    }

    @Override
    public Set<Booking> all() {
        return this.bookings;
    }

    @Override
    public void removeAll() {
        this.bookings.clear();
    }

    @Override
    public Set<Booking> allByFlightId(String flightId) {
        return this.bookings.stream()
                .filter(booking -> booking.getFlight().getFlightIdValue().equals(flightId))
                .collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public Set<Booking> allBookedOn(LocalDateTime bookingOn) {
        return this.bookings.stream()
                .filter(booking -> booking.getBookingOn().equals(bookingOn))
                .collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public Optional<Booking> ByCustomerIdAndFlightId(String customerId, String flightId) {
        return this.bookings.stream()
                .filter(booking -> booking.getCustomer().getCustomerIdValue().equals(customerId) &&
                        booking.getFlight().getFlightIdValue().equals(flightId))
                .findFirst();
    }
}
