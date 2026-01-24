package service;

import exceptions.InvalidInputException;
import exceptions.ResourceNotFoundException;
import models.Mission;
import models.Pilot;
import models.Ship;
import repository.MissionRepository;
import repository.PilotRepository;
import repository.ShipRepository;

import java.util.List;

public class MissionService {
    private final MissionRepository missions = new MissionRepository();
    private final PilotRepository pilots = new PilotRepository();
    private final ShipRepository ships = new ShipRepository();

    public int create(Mission m, int pilotId, int shipId) {
        if (m == null) throw new InvalidInputException("mission is null");
        m.validate();

        Pilot p = pilots.getById(pilotId);
        if (p == null) throw new ResourceNotFoundException("pilot not found: " + pilotId);

        Ship s = ships.getById(shipId);
        if (s == null) throw new ResourceNotFoundException("ship not found: " + shipId);

        if (!p.isReady()) throw new InvalidInputException("pilot not ready (active=false)");
        if (!s.isReady()) throw new InvalidInputException("ship not ready (status must be DOCKED)");

        double requiredFuel = s.fuelNeeded(m.getDistanceKm());
        if (s.getFuelLevel() < requiredFuel)
            throw new InvalidInputException("not enough fuel: need " + requiredFuel + ", have " + s.getFuelLevel());

        m.setRequiredFuel(requiredFuel);

        int missionId = missions.create(
                m.getName(),
                m.getDistanceKm(),
                requiredFuel,
                shipId,
                pilotId
        );

        s.setFuelLevel(s.getFuelLevel() - requiredFuel);
        s.setStatus("IN_MISSION");
        ships.update(shipId, s);

        return missionId;
    }

    public List<String> getAllSimple() {
        return missions.getAllSimple();
    }

    public void delete(int id) {
        if (!missions.delete(id)) throw new ResourceNotFoundException("mission not found: " + id);
    }
}
