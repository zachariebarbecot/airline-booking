package com.zbar.exercises.airline.booking.adapters.secondaries.gateways.real.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Entity
@Table(name = "customers")
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEntity implements Serializable {

    private static final long serialVersionUID = 7037876011280537163L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "number", nullable = false, unique = true)
    private String number;
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
}
