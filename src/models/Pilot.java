package models;

import exceptions.InvalidInputException;
import interfaces.ReadyCheck;

public class Pilot extends BaseEntity implements ReadyCheck {
    private String licenseCode;
    private boolean active;

    public Pilot(int id, String name, String licenseCode, boolean active) {
        super(id, name);
        this.licenseCode = licenseCode;
        this.active = active;
    }

    @Override public void validate() {
        if (name == null || name.trim().isEmpty()) throw new InvalidInputException("pilot name required");
        if (licenseCode == null || licenseCode.trim().isEmpty()) throw new InvalidInputException("license required");
    }

    @Override public String getType() { return "PILOT"; }
    @Override public boolean isReady() { return active; }

    public String getLicenseCode() { return licenseCode; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
