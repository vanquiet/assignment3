package controller;

import exceptions.DuplicateResourceException;
import exceptions.InvalidInputException;
import exceptions.ResourceNotFoundException;
import models.BaseEntity;
import models.Mission;
import models.Pilot;
import models.Ship;
import service.MissionService;
import service.PilotService;
import service.ShipService;

import java.util.List;

public class Main {

    private static void section(String t) {
        System.out.println("\n=== " + t + " ===");
    }

    public static void main(String[] args) {

        PilotService pilotService = new PilotService();
        ShipService shipService = new ShipService();
        MissionService missionService = new MissionService();

        section("POLYMORPHISM");
        Pilot polyPilot = new Pilot(0, "PolyPilot", "LIC-" + System.currentTimeMillis(), true);
        Ship polyShip = new Ship(0, "PolyShip", 100, "DOCKED");
        for (BaseEntity e : List.of(polyPilot, polyShip)) System.out.println(e.shortInfo());

        section("CREATE PILOT + SHIP");
        String license = "LIC-" + System.currentTimeMillis();
        Pilot p = pilotService.create(new Pilot(0, "Aisha", license, true));
        Ship s = shipService.create(new Ship(0, "Falcon", 500, "DOCKED"));
        System.out.println("Pilot id=" + p.getId());
        System.out.println("Ship id=" + s.getId());

        section("DUPLICATE");
        try {
            pilotService.create(new Pilot(0, "CopyPilot", license, true));
        } catch (DuplicateResourceException ex) {
            System.out.println(ex.getMessage());
        }

        section("MISSION CREATE");
        Mission m = new Mission(0, "Supply Run", 200, p, s);
        int missionId = missionService.create(m, p.getId(), s.getId());
        System.out.println("Mission id=" + missionId);
        System.out.println(missionService.getAllSimple());

        section("NOT FOUND");
        try {
            pilotService.getById(999999);
        } catch (ResourceNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        section("INVALID");
        try {
            pilotService.create(new Pilot(0, "", "X", true));
        } catch (InvalidInputException ex) {
            System.out.println(ex.getMessage());
        }

        section("DELETE");
        missionService.delete(missionId);
        shipService.delete(s.getId());
        pilotService.delete(p.getId());
        System.out.println("deleted");
    }
}




