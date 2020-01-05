package com.zbar.exercises.airline.booking.adapters.secondaries.gateways.real.repositories.booking;

import com.zbar.exercises.airline.booking.core.entities.Booking;
import com.zbar.exercises.airline.booking.core.gateways.repositories.BookingRepository;
import lombok.AllArgsConstructor;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@AllArgsConstructor
public class JpaBookingRepository implements BookingRepository {

    private final SpringJpaBookingRepository repository;

    @Override
    public void save(Booking booking) {
        this.saveAll(Collections.singleton(booking));
    }

    @Override
    public void saveAll(Set<Booking> bookings) {
        this.repository.saveAll(
                bookings.stream()
                        .map(BookingMapper::toEntity)
                        .collect(Collectors.toSet())
        );
    }

    @Override
    public Set<Booking> all() {
        return this.repository.findAll().stream()
                .map(BookingMapper::toDomain)
                .collect(Collectors.toSet());
    }

    @Override
    public void removeAll() {
        this.repository.deleteAll();
    }

    @Override
    public Set<Booking> allByFlightId(String flightId) {
        return this.repository.findAllByFlight_Number(flightId).stream()
                .map(BookingMapper::toDomain)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Booking> allBookedOn(LocalDateTime bookingOn) {
        return this.repository.findAllByBookingOn(bookingOn).stream()
                .map(BookingMapper::toDomain)
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<Booking> ByCustomerIdAndFlightId(String customerId, String flightId) {
        return this.repository.findByCustomer_NumberAndFlight_Number(customerId, flightId)
                .map(BookingMapper::toDomain);
    }
}
