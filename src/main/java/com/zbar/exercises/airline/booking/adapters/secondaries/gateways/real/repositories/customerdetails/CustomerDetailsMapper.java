package com.zbar.exercises.airline.booking.adapters.secondaries.gateways.real.repositories.customerdetails;

import com.zbar.exercises.airline.booking.adapters.secondaries.gateways.real.entities.CustomerDetailsEntity;
import com.zbar.exercises.airline.booking.adapters.secondaries.gateways.real.repositories.customer.CustomerMapper;
import com.zbar.exercises.airline.booking.core.entities.Country;
import com.zbar.exercises.airline.booking.core.entities.CustomerDetails;

public class CustomerDetailsMapper {

    public static CustomerDetails toDomain(CustomerDetailsEntity entity) {
        var details = new CustomerDetails(
                CustomerMapper.toDomain(entity.getCustomer()),
                entity.getFirstName(),
                entity.getLastName(),
                Country.valueOf(entity.getCountry())
        );
        details.identity(entity.getId());
        return details;
    }

    public static CustomerDetailsEntity toEntity(CustomerDetails domain) {
        return new CustomerDetailsEntity(
                domain.identity(),
                domain.getFirstName(),
                domain.getLastName(),
                domain.getCountry().name(),
                CustomerMapper.toEntity(domain.getCustomer())
        );
    }
}
