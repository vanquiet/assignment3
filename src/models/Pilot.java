package models;

import interfaces.ReadyCheck;

public class Pilot extends BaseEntity implements ReadyCheck {

    private String licenseCode;
    private boolean active;

    public Pilot() {
    }

    public Pilot(int id, String name, String licenseCode, boolean active) {
        this.id = id;
        this.name = name;
        this.licenseCode = licenseCode;
        this.active = active;
    }

    @Override
    public String getType() {
        return "PILOT";
    }

    @Override
    public void validate() {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("pilot name required");
        }
        if (licenseCode == null || licenseCode.isBlank()) {
            throw new IllegalArgumentException("license code required");
        }
    }

    public String getLicenseCode() {
        return licenseCode;
    }

    public void setLicenseCode(String licenseCode) {
        this.licenseCode = licenseCode;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean isReady() {
        return active;
    }

    @Override
    public String toString() {
        return shortInfo();
    }
}
