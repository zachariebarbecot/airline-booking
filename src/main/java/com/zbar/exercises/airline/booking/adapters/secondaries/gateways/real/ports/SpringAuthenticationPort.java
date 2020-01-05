package com.zbar.exercises.airline.booking.adapters.secondaries.gateways.real.ports;

import com.zbar.exercises.airline.booking.adapters.secondaries.configurations.security.SpringUserDetails;
import com.zbar.exercises.airline.booking.core.entities.Customer;
import com.zbar.exercises.airline.booking.core.gateways.ports.AuthenticationPort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static java.util.Optional.ofNullable;

public class SpringAuthenticationPort implements AuthenticationPort {

    @Override
    public void authenticate(Customer customer) {
        // nothing
    }

    @Override
    public Optional<Customer> currentCustomer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var userDetails = (SpringUserDetails) authentication.getPrincipal();
        return ofNullable(userDetails.getCustomer());
    }
}
