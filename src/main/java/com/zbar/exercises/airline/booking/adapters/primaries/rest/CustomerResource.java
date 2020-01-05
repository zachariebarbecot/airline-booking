package com.zbar.exercises.airline.booking.adapters.primaries.rest;

import com.zbar.exercises.airline.booking.core.usecases.customers.retrieval.CustomerDTO;
import com.zbar.exercises.airline.booking.core.usecases.customers.retrieval.RetrieveCustomers;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("api/customers")
public class CustomerResource {

    private final RetrieveCustomers retrieveCustomers;

    @GetMapping
    public @ResponseBody
    Set<CustomerDTO> getAll() {
        return this.retrieveCustomers.execute();
    }
}
