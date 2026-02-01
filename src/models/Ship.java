package models;

import interfaces.ReadyCheck;

public class Ship extends BaseEntity implements ReadyCheck {

    private double fuelLevel;
    private String status;

    public Ship() {
    }

    public Ship(int id, String name, double fuelLevel, String status) {
        this.id = id;
        this.name = name;
        this.fuelLevel = fuelLevel;
        this.status = status;
    }

    @Override
    public String getType() {
        return "SHIP";
    }

    @Override
    public void validate() {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("ship name required");
        }
        if (fuelLevel < 0) {
            throw new IllegalArgumentException("fuel level must be >= 0");
        }
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("status required");
        }
    }

    public double fuelNeeded(double distanceKm) {
        return distanceKm;
    }

    public double getFuelLevel() {
        return fuelLevel;
    }

    public void setFuelLevel(double fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean isReady() {
        return "DOCKED".equalsIgnoreCase(status) && fuelLevel > 0;
    }

    @Override
    public String toString() {
        return shortInfo();
    }
}

