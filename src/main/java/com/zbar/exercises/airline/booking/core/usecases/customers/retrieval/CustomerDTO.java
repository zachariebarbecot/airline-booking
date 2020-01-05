package com.zbar.exercises.airline.booking.core.usecases.customers.retrieval;

import com.zbar.exercises.airline.booking.core.entities.CustomerId;
import lombok.Data;

@Data
public class CustomerDTO {

    private CustomerId customerId;
    private String username;
    private CustomerDetailsDTO details;

    public CustomerDTO(CustomerId customerId, String username, CustomerDetailsDTO details) {
        this.customerId = customerId;
        this.username = username;
        this.details = details;
    }
}
