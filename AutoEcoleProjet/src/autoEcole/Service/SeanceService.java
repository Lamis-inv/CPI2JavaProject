package autoEcole.Service;

import autoEcole.Entities.Seance;
import autoEcole.Repository.SeanceRepository;

public class SeanceService {

    private final SeanceRepository repository = new SeanceRepository();

    public void addSeance(Seance s) {
        repository.add(s);
    }

    public Seance[] getAll() {
        return repository.getAll();
    }

    public void findAll() {
        repository.findAll();
    }

    public Seance getById(int id) {
        return repository.getById(id);
    }

    public void updateSeance(int id, Seance updated) {
        repository.update(id, updated);
    }

    public void deleteSeance(int id) {
        repository.delete(id);
    }

    public void findById(int id) {
        repository.findById(id);
    }
}

