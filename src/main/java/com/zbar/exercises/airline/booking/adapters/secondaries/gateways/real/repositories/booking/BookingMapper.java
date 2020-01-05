package com.zbar.exercises.airline.booking.adapters.secondaries.gateways.real.repositories.booking;

import com.zbar.exercises.airline.booking.adapters.secondaries.gateways.real.entities.BookingEntity;
import com.zbar.exercises.airline.booking.adapters.secondaries.gateways.real.repositories.customer.CustomerMapper;
import com.zbar.exercises.airline.booking.adapters.secondaries.gateways.real.repositories.flight.FlightMapper;
import com.zbar.exercises.airline.booking.core.entities.Booking;
import com.zbar.exercises.airline.booking.core.entities.BookingId;

public class BookingMapper {

    public static Booking toDomain(BookingEntity entity) {
        var booking = new Booking(
                new BookingId(entity.getNumber()),
                CustomerMapper.toDomain(entity.getCustomer()),
                FlightMapper.toDomain(entity.getFlight()),
                entity.getBookingOn()
        );
        booking.identity(entity.getId());
        return booking;
    }

    public static BookingEntity toEntity(Booking domain) {
        return new BookingEntity(
                domain.identity(),
                domain.getBookingIdValue(),
                domain.getBookingOn(),
                CustomerMapper.toEntity(domain.getCustomer()),
                FlightMapper.toEntity(domain.getFlight())
        );
    }
}
