package com.zbar.exercises.airline.booking.core.usecases.booking;

import com.zbar.exercises.airline.booking.core.entities.*;
import com.zbar.exercises.airline.booking.core.exceptions.AlreadyExistsException;
import com.zbar.exercises.airline.booking.core.exceptions.FulledFlightException;
import com.zbar.exercises.airline.booking.core.exceptions.UnauthorizedAccessException;
import com.zbar.exercises.airline.booking.core.exceptions.UnknownException;
import com.zbar.exercises.airline.booking.core.gateways.ports.AuthenticationPort;
import com.zbar.exercises.airline.booking.core.gateways.ports.IdGeneratorPort;
import com.zbar.exercises.airline.booking.core.gateways.repositories.BookingRepository;
import com.zbar.exercises.airline.booking.core.gateways.repositories.FlightRepository;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class BookFlight {

    private final BookingRepository bookingRepository;
    private final FlightRepository flightRepository;
    private final AuthenticationPort authenticationPort;
    private final IdGeneratorPort idGeneratorPort;

    public void execute(FlightId flightId) {
        var customer = this.authenticationPort.currentCustomer()
                .orElseThrow(() -> new UnauthorizedAccessException("Must be authenticated to do this action"));
        this.bookingRepository.ByCustomerIdAndFlightId(customer.getCustomerIdValue(), flightId.getId())
                .ifPresent(booking -> {
                    throw new AlreadyExistsException("It is already booked");
                });
        this.registerBooking(flightId, customer);
    }

    private void registerBooking(FlightId flightId, Customer customer) {
        var flight = this.flightRepository.byFlightId(flightId.getId())
                .orElseThrow(() -> new UnknownException("Flight not found"));
        var numberOfPassengers = this.bookingRepository.allByFlightId(flightId.getId()).size();
        if (this.isFlightFull(flight, numberOfPassengers)) {
            throw new FulledFlightException("No vacant places for this flight");
        }
        var booking = new Booking(
                new BookingId(this.idGeneratorPort.generate()),
                customer,
                flight,
                LocalDateTime.now()
        );
        this.bookingRepository.save(booking);
    }

    private boolean isFlightFull(Flight flight, int numberOfPassengers) {
        return numberOfPassengers >= flight.getMaxNumberPassengers();
    }

}
