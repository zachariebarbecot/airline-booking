package com.zbar.exercises.airline.booking.adapters.secondaries.gateways.real.ports;

import com.zbar.exercises.airline.booking.core.gateways.ports.PasswordEncoderPort;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
public class SpringPasswordEncoderPort implements PasswordEncoderPort {

    private final PasswordEncoder encoder;

    @Override
    public String encode(String password) {
        return this.encoder.encode(password);
    }
}
