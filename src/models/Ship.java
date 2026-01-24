package models;

import exceptions.InvalidInputException;
import interfaces.FuelConsumable;
import interfaces.ReadyCheck;

public class Ship extends BaseEntity implements ReadyCheck, FuelConsumable {
    public static final String DOCKED = "DOCKED";

    private double fuelLevel;
    private String status;

    public Ship(int id, String name, double fuelLevel, String status) {
        super(id, name);
        this.fuelLevel = fuelLevel;
        this.status = status;
    }

    @Override public void validate() {
        if (name == null || name.trim().isEmpty()) throw new InvalidInputException("ship name required");
        if (fuelLevel < 0) throw new InvalidInputException("fuel must be >= 0");
        if (status == null || status.trim().isEmpty()) throw new InvalidInputException("status required");
    }

    @Override public String getType() { return "SHIP"; }
    @Override public boolean isReady() { return DOCKED.equalsIgnoreCase(status); }
    @Override public double fuelNeeded(double km) { return km * 0.5; }

    public double getFuelLevel() { return fuelLevel; }
    public void setFuelLevel(double fuelLevel) { this.fuelLevel = fuelLevel; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
