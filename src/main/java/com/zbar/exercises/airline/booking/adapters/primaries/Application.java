package com.zbar.exercises.airline.booking.adapters.primaries;

import com.zbar.exercises.airline.booking.core.entities.Country;
import com.zbar.exercises.airline.booking.core.usecases.customers.registration.CustomerDTO;
import com.zbar.exercises.airline.booking.core.usecases.customers.registration.CustomerDetailsDTO;
import com.zbar.exercises.airline.booking.core.usecases.customers.registration.RegisterCustomer;
import com.zbar.exercises.airline.booking.core.usecases.flights.registration.FlightDTO;
import com.zbar.exercises.airline.booking.core.usecases.flights.registration.RegisterFlight;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.Month;

@SpringBootApplication(scanBasePackages = {
        "com.zbar.exercises.airline.booking.adapters.secondaries.configurations",
        "com.zbar.exercises.airline.booking.adapters.primaries.rest"
})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner runner(RegisterCustomer registerCustomer, RegisterFlight registerFlight) {
        var customer = new CustomerDTO("zbarbecot", "password");
        var details = new CustomerDetailsDTO("Zacharie", "BARBECOT", Country.FR);
        var flight = new FlightDTO(
                Country.FR,
                Country.EN,
                LocalDateTime.of(2020, Month.JANUARY, 1, 9, 00),
                LocalDateTime.of(2020, Month.JANUARY, 1, 11, 00),
                250
        );
        return args -> {
            registerCustomer.execute(customer, details);
            registerFlight.execute(flight);
        };
    }
}
