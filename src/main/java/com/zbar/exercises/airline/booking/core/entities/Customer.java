package com.zbar.exercises.airline.booking.core.entities;

import lombok.Value;

@Value
public class Customer extends Entity {

    private CustomerId customerId;
    private String username;
    private String password;

    public String getCustomerIdValue() {
        return this.customerId.getId();
    }
}
