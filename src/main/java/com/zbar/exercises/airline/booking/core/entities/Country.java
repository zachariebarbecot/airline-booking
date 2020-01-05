package com.zbar.exercises.airline.booking.core.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Country {

    AD("ANDORRA"),
    BE("BELGIUM"),
    CH("SWITZERLAND"),
    DE("GERMANY"),
    EN("UNITED KINGDOM"),
    FR("FRANCE"),
    IT("ITALY"),
    MC("MONACO"),
    SP("SPAIN"),
    UKN("UNKNOWN");

    private String value;
}
