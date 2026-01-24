package service;

import exceptions.InvalidInputException;
import exceptions.ResourceNotFoundException;
import models.Ship;
import repository.ShipRepository;

import java.util.List;

public class ShipService {
    private final ShipRepository repo = new ShipRepository();

    public Ship create(Ship s) {
        if (s == null) throw new InvalidInputException("ship is null");
        s.validate();
        return repo.create(s);
    }

    public Ship getById(int id) {
        Ship s = repo.getById(id);
        if (s == null) throw new ResourceNotFoundException("ship not found: " + id);
        return s;
    }

    public List<Ship> getAll() {
        return repo.getAll();
    }

    public void update(int id, Ship s) {
        if (s == null) throw new InvalidInputException("ship is null");
        s.validate();
        if (!repo.update(id, s))
            throw new ResourceNotFoundException("ship not found: " + id);
    }

    public void delete(int id) {
        if (!repo.delete(id))
            throw new ResourceNotFoundException("ship not found: " + id);
    }
}
