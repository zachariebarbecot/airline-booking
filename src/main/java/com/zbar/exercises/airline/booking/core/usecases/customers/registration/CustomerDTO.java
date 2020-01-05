package com.zbar.exercises.airline.booking.core.usecases.customers.registration;

import com.zbar.exercises.airline.booking.core.exceptions.RequiredParameterException;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class CustomerDTO {

    private String username;
    private String password;

    public CustomerDTO(String username, String password) {
        if (StringUtils.isBlank(username)) {
            throw new RequiredParameterException("username is required");
        }
        if (StringUtils.isBlank(password)) {
            throw new RequiredParameterException("password is required");
        }
        this.username = username;
        this.password = password;
    }
}
