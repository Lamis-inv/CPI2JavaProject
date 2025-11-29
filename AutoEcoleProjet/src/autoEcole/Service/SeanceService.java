package autoEcole.Service;

import autoEcole.Entities.Candidat;
import autoEcole.Entities.Moniteur;
import autoEcole.Entities.Seance;
import autoEcole.Entities.Vehicule;
import autoEcole.Repository.CandidatRepository;
import autoEcole.Repository.MoniteurRepository;
import autoEcole.Repository.SeanceRepository;

import java.time.LocalDate;
import java.time.LocalTime;

public class SeanceService{

    private final SeanceRepository seanceRepo = new SeanceRepository();
    private final CandidatRepository candidatRepo = new CandidatRepository();
    private final MoniteurRepository moniteurRepo = new MoniteurRepository();

    public void addSeance(Seance s) throws IllegalArgumentException {
        Candidat c = s.getCandidat();
        Moniteur m = s.getMoniteur();

        // Rule: Candidate must pass code exam before conduite
        if (s.getType().equalsIgnoreCase("conduite") && !c.getCodeExamPassed()) {
            throw new IllegalArgumentException("Candidate must pass code exam before driving lessons.");
        }

        // Rule: Increment sessions and total price
        if (s.getType().equalsIgnoreCase("code")) {
            c.addCodeSession();
        } else {
            c.addConduiteSession();
        }
        candidatRepo.update(c.getCin(), c);

        // Rule: Increment moniteur hours
        m.setNbHeuresTravaillees(m.getNbHeuresTravaillees() + 1);
        moniteurRepo.update(m.getId(), m);

        // Save seance
        seanceRepo.add(s);
    }

   
    
    public Seance[] getAll() {
        return seanceRepo.getAll();
    }

    public void deleteSeance(int id) {
        seanceRepo.delete(id);
    }

    public Seance getById(int id) {
        return seanceRepo.getById(id);
    }

    public void updateSeance(int id, Seance updated) {
        seanceRepo.update(id, updated);
    }

    public void findAll() {
        seanceRepo.findAll();
    }

    public void findById(int id) {
        seanceRepo.findById(id);
    }


}
