package com.zbar.exercises.airline.booking.core.entities;

import lombok.Value;

@Value
public class CustomerDetails extends Entity {

    private Customer customer;
    private String firstName;
    private String lastName;
    private Country country;

    public String getCustomerIdValue() {
        return this.customer.getCustomerIdValue();
    }
}
