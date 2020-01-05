package com.zbar.exercises.airline.booking.adapters.secondaries.gateways.inmemories.ports;

import com.zbar.exercises.airline.booking.core.gateways.ports.IdGeneratorPort;

public class InMemoryIdGeneratorPort implements IdGeneratorPort {

    @Override
    public String generate() {
        return "123-456-789";
    }
}
