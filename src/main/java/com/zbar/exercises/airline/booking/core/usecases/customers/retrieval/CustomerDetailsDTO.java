package com.zbar.exercises.airline.booking.core.usecases.customers.retrieval;

import com.zbar.exercises.airline.booking.core.entities.Country;
import com.zbar.exercises.airline.booking.core.entities.CustomerId;
import lombok.Data;

@Data
public class CustomerDetailsDTO {

    private String firstName;
    private String lastName;
    private Country country;

    public CustomerDetailsDTO(String firstName, String lastName, Country country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
    }
}
