package com.zbar.exercises.airline.booking.adapters.secondaries.gateways.inmemories.ports;

import com.zbar.exercises.airline.booking.core.gateways.ports.PasswordEncoderPort;

public class InMemoryPasswordEncoderPort implements PasswordEncoderPort {

    @Override
    public String encode(String password) {
        return "encoded" + password;
    }
}
