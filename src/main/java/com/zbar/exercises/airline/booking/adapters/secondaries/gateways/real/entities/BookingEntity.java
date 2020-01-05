package com.zbar.exercises.airline.booking.adapters.secondaries.gateways.real.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "bookings")
@AllArgsConstructor
@NoArgsConstructor
public class BookingEntity implements Serializable {

    private static final long serialVersionUID = -6903416003192203779L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "number", nullable = false, unique = true)
    private String number;
    @Column(name = "bookingon", nullable = false)
    private LocalDateTime bookingOn;
    @OneToOne
    @JoinColumn(name = "customers_id", referencedColumnName = "id")
    private CustomerEntity customer;
    @OneToOne
    @JoinColumn(name = "flights_id", referencedColumnName = "id")
    private FlightEntity flight;
}
