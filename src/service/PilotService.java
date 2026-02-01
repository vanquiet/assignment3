package service;

import exceptions.DuplicateResourceException;
import exceptions.InvalidInputException;
import exceptions.ResourceNotFoundException;
import models.Pilot;
import repository.CrudRepository;

import java.util.List;

public class PilotService {

    private final CrudRepository<Pilot> repo;

    public PilotService(CrudRepository<Pilot> repo) {
        this.repo = repo;
    }

    public Pilot create(Pilot pilot) {
        if (pilot == null) {
            throw new InvalidInputException("pilot is null");
        }

        pilot.validate();

        if (repo instanceof repository.PilotRepository pr) {
            if (pr.existsByLicense(pilot.getLicenseCode())) {
                throw new DuplicateResourceException(
                        "license already exists: " + pilot.getLicenseCode()
                );
            }
        }

        return repo.create(pilot);
    }

    public Pilot getById(int id) {
        Pilot p = repo.getById(id);
        if (p == null) {
            throw new ResourceNotFoundException("pilot not found: " + id);
        }
        return p;
    }

    public List<Pilot> getAll() {
        return repo.getAll();
    }

    public void update(int id, Pilot pilot) {
        if (pilot == null) {
            throw new InvalidInputException("pilot is null");
        }

        pilot.validate();

        boolean updated = repo.update(id, pilot);
        if (!updated) {
            throw new ResourceNotFoundException("pilot not found: " + id);
        }
    }

    public void delete(int id) {
        boolean deleted = repo.delete(id);
        if (!deleted) {
            throw new ResourceNotFoundException("pilot not found: " + id);
        }
    }
}
