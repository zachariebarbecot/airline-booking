package com.zbar.exercises.airline.booking.core.exceptions;

public class RequiredParameterException extends RuntimeException {

    public RequiredParameterException(String message) {
        super(message);
    }
}
