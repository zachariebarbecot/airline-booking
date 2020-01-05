package com.zbar.exercises.airline.booking.adapters.secondaries.gateways.real.repositories.customer;

import com.zbar.exercises.airline.booking.adapters.secondaries.gateways.real.entities.CustomerEntity;
import com.zbar.exercises.airline.booking.core.entities.Customer;
import com.zbar.exercises.airline.booking.core.entities.CustomerId;

public class CustomerMapper {

    public static Customer toDomain(CustomerEntity entity) {
        var customer = new Customer(
                new CustomerId(entity.getNumber()),
                entity.getUsername(),
                entity.getPassword()
        );
        customer.identity(entity.getId());
        return customer;
    }

    public static CustomerEntity toEntity(Customer domain) {
        return new CustomerEntity(
                domain.identity(),
                domain.getCustomerIdValue(),
                domain.getUsername(),
                domain.getPassword()
        );
    }
}
