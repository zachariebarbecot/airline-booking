package com.zbar.exercises.airline.booking.adapters.secondaries.configurations;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = {
        "com.zbar.exercises.airline.booking.adapters.secondaries.gateways.real.entities"
})
@EnableJpaRepositories(basePackages = {
        "com.zbar.exercises.airline.booking.adapters.secondaries.gateways.real.repositories"
})
public class SpringDataConfig {
}
