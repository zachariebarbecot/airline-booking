package com.zbar.exercises.airline.booking.adapters.secondaries.gateways.real.ports;

import com.zbar.exercises.airline.booking.core.gateways.ports.IdGeneratorPort;

import java.util.UUID;

public class UuidIdGeneratorPort implements IdGeneratorPort {

    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
