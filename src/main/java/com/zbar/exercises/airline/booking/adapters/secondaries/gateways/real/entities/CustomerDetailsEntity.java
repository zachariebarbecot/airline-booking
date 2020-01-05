package com.zbar.exercises.airline.booking.adapters.secondaries.gateways.real.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Entity
@Table(name = "customerdetails")
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDetailsEntity implements Serializable {

    private static final long serialVersionUID = -4687078617994832655L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "firstname", nullable = false)
    private String firstName;
    @Column(name = "lastname", nullable = false)
    private String lastName;
    @Column(name = "country", nullable = false)
    private String country;
    @OneToOne
    @JoinColumn(name = "customers_id", referencedColumnName = "id")
    private CustomerEntity customer;
}
