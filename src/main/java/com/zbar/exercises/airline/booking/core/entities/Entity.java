package com.zbar.exercises.airline.booking.core.entities;

public abstract class Entity {

    private Long surrogateId = -1L;

    public Long identity() {
        return this.surrogateId == null ? -1 : this.surrogateId;
    }

    public void identity(Long identity) {
        this.surrogateId = identity;
    }

    public boolean isIdentified() {
        return identity() > -1;
    }
}
