package com.zbar.exercises.airline.booking.adapters.secondaries.configurations;

import com.zbar.exercises.airline.booking.core.gateways.ports.AuthenticationPort;
import com.zbar.exercises.airline.booking.core.gateways.ports.IdGeneratorPort;
import com.zbar.exercises.airline.booking.core.gateways.ports.PasswordEncoderPort;
import com.zbar.exercises.airline.booking.core.gateways.repositories.BookingRepository;
import com.zbar.exercises.airline.booking.core.gateways.repositories.CustomerDetailsRepository;
import com.zbar.exercises.airline.booking.core.gateways.repositories.CustomerRepository;
import com.zbar.exercises.airline.booking.core.gateways.repositories.FlightRepository;
import com.zbar.exercises.airline.booking.core.usecases.booking.BookFlight;
import com.zbar.exercises.airline.booking.core.usecases.customers.registration.RegisterCustomer;
import com.zbar.exercises.airline.booking.core.usecases.customers.retrieval.RetrieveCustomers;
import com.zbar.exercises.airline.booking.core.usecases.flights.registration.RegisterFlight;
import com.zbar.exercises.airline.booking.core.usecases.flights.retrieval.RetrieveFlights;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesConfig {

    @Bean
    public RegisterCustomer registerCustomer(CustomerRepository customerRepository,
                                             CustomerDetailsRepository customerDetailsRepository,
                                             IdGeneratorPort idGeneratorPort,
                                             PasswordEncoderPort passwordEncoderPort) {
        return new RegisterCustomer(customerRepository, customerDetailsRepository, idGeneratorPort, passwordEncoderPort);
    }

    @Bean
    public RetrieveCustomers retrieveCustomers(CustomerRepository customerRepository,
                                               CustomerDetailsRepository customerDetailsRepository) {
        return new RetrieveCustomers(customerRepository, customerDetailsRepository);
    }

    @Bean
    public RegisterFlight registerFlight(FlightRepository flightRepository, IdGeneratorPort idGeneratorPort) {
        return new RegisterFlight(flightRepository, idGeneratorPort);
    }

    @Bean
    public RetrieveFlights retrieveFlights(FlightRepository flightRepository) {
        return new RetrieveFlights(flightRepository);
    }

    @Bean
    public BookFlight bookFlight(BookingRepository bookingRepository,
                                 FlightRepository flightRepository,
                                 AuthenticationPort authenticationPort,
                                 IdGeneratorPort idGeneratorPort) {
        return new BookFlight(bookingRepository, flightRepository, authenticationPort, idGeneratorPort);
    }
}
