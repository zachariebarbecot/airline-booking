package com.zbar.exercises.airline.booking.adapters.secondaries.gateways.real.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "flights")
@AllArgsConstructor
@NoArgsConstructor
public class FlightEntity implements Serializable {

    private static final long serialVersionUID = 884949331204581116L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "number", nullable = false, unique = true)
    private String number;
    @Column(name = "flyingfrom", nullable = false)
    private String flyingFrom;
    @Column(name = "flyingto", nullable = false)
    private String flyingTo;
    @Column(name = "departingdate", nullable = false)
    private LocalDateTime departing;
    @Column(name = "returningdate", nullable = false)
    private LocalDateTime returning;
    @Column(name = "maxnumberpassengers", nullable = false)
    private Integer maxNumberPassengers;
}
