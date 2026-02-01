package controller;

import interfaces.Printable;
import repository.PilotRepository;
import repository.ShipRepository;
import service.PilotService;
import service.ShipService;
import util.ReflectionUtils;

public class Main {

    public static void main(String[] args) {

        PilotService pilotService =
                new PilotService(new PilotRepository());

        ShipService shipService =
                new ShipService(new ShipRepository());

        Printable.header("LAMBDA");
        var pilots = pilotService.getAll();
        pilots.sort((a, b) -> a.getName().compareTo(b.getName()));
        System.out.println(pilots);

        Printable.header("REFLECTION");
        if (!pilots.isEmpty()) {
            ReflectionUtils.printInfo(pilots.get(0));
        }

    }
}

