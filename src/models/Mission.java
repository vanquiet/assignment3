package models;

import exceptions.InvalidInputException;

public class Mission {
    private int id;
    private String name;
    private double distanceKm;
    private double requiredFuel;

    private Pilot pilot;
    private Ship ship;

    public Mission(int id, String name, double distanceKm, Pilot pilot, Ship ship) {
        this.id = id;
        this.name = name;
        this.distanceKm = distanceKm;
        this.pilot = pilot;
        this.ship = ship;
    }

    public void validate() {
        if (name == null || name.trim().isEmpty()) throw new InvalidInputException("mission name required");
        if (distanceKm <= 0) throw new InvalidInputException("distance must be > 0");
        if (pilot == null) throw new InvalidInputException("pilot required");
        if (ship == null) throw new InvalidInputException("ship required");
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getDistanceKm() { return distanceKm; }

    public double getRequiredFuel() { return requiredFuel; }
    public void setRequiredFuel(double requiredFuel) { this.requiredFuel = requiredFuel; }

    public Pilot getPilot() { return pilot; }
    public Ship getShip() { return ship; }
}

