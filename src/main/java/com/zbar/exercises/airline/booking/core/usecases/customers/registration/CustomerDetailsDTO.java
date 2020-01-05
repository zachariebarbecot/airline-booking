package com.zbar.exercises.airline.booking.core.usecases.customers.registration;

import com.zbar.exercises.airline.booking.core.entities.Country;
import com.zbar.exercises.airline.booking.core.exceptions.RequiredParameterException;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class CustomerDetailsDTO {

    private String firstName;
    private String lastName;
    private Country country;

    public CustomerDetailsDTO(String firstName, String lastName, Country country) {
        if (StringUtils.isBlank(firstName)) {
            throw new RequiredParameterException("firstName is required");
        }
        if (StringUtils.isBlank(lastName)) {
            throw new RequiredParameterException("lastName is required");
        }
        if (country == null) {
            throw new RequiredParameterException("country is required");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
    }
}
