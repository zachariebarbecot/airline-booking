package com.zbar.exercises.airline.booking.adapters.secondaries.gateways.real.repositories.booking;

import com.zbar.exercises.airline.booking.adapters.secondaries.gateways.real.entities.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Repository
public interface SpringJpaBookingRepository extends JpaRepository<BookingEntity, Long> {

    Set<BookingEntity> findAllByFlight_Number(String flightId);

    Set<BookingEntity> findAllByBookingOn(LocalDateTime bookingOn);

    Optional<BookingEntity> findByCustomer_NumberAndFlight_Number(String customerId, String flightId);
}
