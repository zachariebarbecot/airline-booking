package com.zbar.exercises.airline.booking.adapters.secondaries.configurations;

import com.zbar.exercises.airline.booking.adapters.secondaries.gateways.real.ports.SpringAuthenticationPort;
import com.zbar.exercises.airline.booking.adapters.secondaries.gateways.real.ports.SpringPasswordEncoderPort;
import com.zbar.exercises.airline.booking.adapters.secondaries.gateways.real.ports.UuidIdGeneratorPort;
import com.zbar.exercises.airline.booking.adapters.secondaries.gateways.real.repositories.booking.JpaBookingRepository;
import com.zbar.exercises.airline.booking.adapters.secondaries.gateways.real.repositories.booking.SpringJpaBookingRepository;
import com.zbar.exercises.airline.booking.adapters.secondaries.gateways.real.repositories.customer.JpaCustomerRepository;
import com.zbar.exercises.airline.booking.adapters.secondaries.gateways.real.repositories.customer.SpringJpaCustomerRepository;
import com.zbar.exercises.airline.booking.adapters.secondaries.gateways.real.repositories.customerdetails.JpaCustomerDetailsRepository;
import com.zbar.exercises.airline.booking.adapters.secondaries.gateways.real.repositories.customerdetails.SpringJpaCustomerDetailsRepository;
import com.zbar.exercises.airline.booking.adapters.secondaries.gateways.real.repositories.flight.JpaFlightRepository;
import com.zbar.exercises.airline.booking.adapters.secondaries.gateways.real.repositories.flight.SpringJpaFlightRepository;
import com.zbar.exercises.airline.booking.core.gateways.ports.AuthenticationPort;
import com.zbar.exercises.airline.booking.core.gateways.ports.IdGeneratorPort;
import com.zbar.exercises.airline.booking.core.gateways.ports.PasswordEncoderPort;
import com.zbar.exercises.airline.booking.core.gateways.repositories.BookingRepository;
import com.zbar.exercises.airline.booking.core.gateways.repositories.CustomerDetailsRepository;
import com.zbar.exercises.airline.booking.core.gateways.repositories.CustomerRepository;
import com.zbar.exercises.airline.booking.core.gateways.repositories.FlightRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class GatewaysConfig {

    @Bean
    public AuthenticationPort authenticationPort() {
        return new SpringAuthenticationPort();
    }

    @Bean
    public IdGeneratorPort idGeneratorPort() {
        return new UuidIdGeneratorPort();
    }

    @Bean
    public PasswordEncoderPort passwordEncoderPort(PasswordEncoder encoder) {
        return new SpringPasswordEncoderPort(encoder);
    }

    @Bean
    public CustomerRepository customerRepository(SpringJpaCustomerRepository repository) {
        return new JpaCustomerRepository(repository);
    }

    @Bean
    public CustomerDetailsRepository customerDetailsRepository(SpringJpaCustomerDetailsRepository repository) {
        return new JpaCustomerDetailsRepository(repository);
    }

    @Bean
    public FlightRepository flightRepository(SpringJpaFlightRepository repository) {
        return new JpaFlightRepository(repository);
    }

    @Bean
    public BookingRepository bookingRepository(SpringJpaBookingRepository repository) {
        return new JpaBookingRepository(repository);
    }
}
