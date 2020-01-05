package com.zbar.exercises.airline.booking.core.exceptions;

public class FulledFlightException extends RuntimeException {

    public FulledFlightException(String message) {
        super(message);
    }
}
