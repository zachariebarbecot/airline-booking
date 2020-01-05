package com.zbar.exercises.airline.booking.adapters.primaries.rest;

import com.zbar.exercises.airline.booking.core.usecases.flights.retrieval.FlightDTO;
import com.zbar.exercises.airline.booking.core.usecases.flights.retrieval.RetrieveFlights;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("api/flights")
public class FlightResource {

    private final RetrieveFlights retrieveFlights;

    @GetMapping
    public @ResponseBody
    Set<FlightDTO> getAll() {
        return this.retrieveFlights.execute();
    }
}
