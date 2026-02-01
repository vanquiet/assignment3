package controller;

import repository.PilotRepository;
import repository.ShipRepository;
import service.PilotService;
import service.ShipService;

public class Main {

    public static void main(String[] args) {

        PilotService pilotService =
                new PilotService(new PilotRepository());

        ShipService shipService =
                new ShipService(new ShipRepository());

        System.out.println("Assignment 4 setup OK");
    }
}
