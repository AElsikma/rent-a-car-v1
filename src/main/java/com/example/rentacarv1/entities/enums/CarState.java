package com.example.rentacarv1.entities.enums;

public enum CarState {
    AVAILABLE("Available"),
    RENTED("Rented"),
    MAINTENANCE("Maintenance");

    private final String state;
    CarState(String state) {
        this.state=state;
    }

    public String getCarState() {
        return state;
    }
}
