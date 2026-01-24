package service;

import exceptions.DuplicateResourceException;
import exceptions.InvalidInputException;
import exceptions.ResourceNotFoundException;
import models.Pilot;
import repository.PilotRepository;

import java.util.List;

public class PilotService {
    private final PilotRepository repo = new PilotRepository();

    public Pilot create(Pilot p) {
        if (p == null) throw new InvalidInputException("pilot is null");
        p.validate();
        if (repo.existsByLicense(p.getLicenseCode()))
            throw new DuplicateResourceException("license already exists: " + p.getLicenseCode());
        return repo.create(p);
    }

    public Pilot getById(int id) {
        Pilot p = repo.getById(id);
        if (p == null) throw new ResourceNotFoundException("pilot not found: " + id);
        return p;
    }

    public List<Pilot> getAll() { return repo.getAll(); }

    public void update(int id, Pilot p) {
        if (p == null) throw new InvalidInputException("pilot is null");
        p.validate();
        if (!repo.update(id, p)) throw new ResourceNotFoundException("pilot not found: " + id);
    }

    public void delete(int id) {
        if (!repo.delete(id)) throw new ResourceNotFoundException("pilot not found: " + id);
    }
}
