package service;

import exceptions.InvalidInputException;
import exceptions.ResourceNotFoundException;
import models.Ship;
import repository.CrudRepository;

import java.util.List;

public class ShipService {

    private final CrudRepository<Ship> repo;

    public ShipService(CrudRepository<Ship> repo) {
        this.repo = repo;
    }

    public Ship create(Ship ship) {
        if (ship == null) {
            throw new InvalidInputException("ship is null");
        }

        ship.validate();
        return repo.create(ship);
    }

    public Ship getById(int id) {
        Ship s = repo.getById(id);
        if (s == null) {
            throw new ResourceNotFoundException("ship not found: " + id);
        }
        return s;
    }

    public List<Ship> getAll() {
        return repo.getAll();
    }

    public void update(int id, Ship ship) {
        if (ship == null) {
            throw new InvalidInputException("ship is null");
        }

        ship.validate();

        boolean updated = repo.update(id, ship);
        if (!updated) {
            throw new ResourceNotFoundException("ship not found: " + id);
        }
    }

    public void delete(int id) {
        boolean deleted = repo.delete(id);
        if (!deleted) {
            throw new ResourceNotFoundException("ship not found: " + id);
        }
    }
}
