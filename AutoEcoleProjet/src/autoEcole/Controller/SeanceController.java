package autoEcole.Controller;

import autoEcole.Entities.Seance;
import autoEcole.Service.SeanceService;

public class SeanceController {
    
    private final SeanceService service = new SeanceService();

    public void add(Seance s) {
        service.addSeance(s);
    }

    public void findAll() {
        service.findAll();
    }

    public Seance getById(int id) {
        return service.getById(id);
    }

    public void update(int id, Seance s) {
        service.updateSeance(id, s);
    }

    public void delete(int id) {
        service.deleteSeance(id);
    }

    public void findById(int id) {
        service.findById(id);
    }
}
