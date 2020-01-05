package com.zbar.exercises.airline.booking.adapters.primaries.rest;

import com.zbar.exercises.airline.booking.core.entities.FlightId;
import com.zbar.exercises.airline.booking.core.usecases.booking.BookFlight;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/bookings")
public class BookingResource {

    private final BookFlight bookFlight;

    @PostMapping
    public void postBooking(@RequestBody FlightRequest request) {
        var flightId = new FlightId(request.getId());
        this.bookFlight.execute(flightId);
    }
}

@Data
class FlightRequest {

    private String id;
}
