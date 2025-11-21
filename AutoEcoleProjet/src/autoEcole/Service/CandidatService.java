package autoEcole.Service;


import autoEcole.Entities.Candidat;
import autoEcole.Entities.Seance;
import autoEcole.Repository.CandidatRepository;

public class CandidatService {
	CandidatRepository cRepository = new CandidatRepository();
	
	public void addCandidat(Candidat c) {
		Candidat can = new Candidat(
                c.getNom(),
                c.getPrenom(),
                c.getAdresse(),
                c.getTelephone(),
                c.getCin(),
                c.getTypePermis(),
                c.getTotalPrice(),
                c.getPaidAmount(),
                c.getSeances() != null ? c.getSeances() : new Seance[0]
        );
		cRepository.add(can);
	}
	
	public void findAllCandidat() {
		 cRepository.findAll();
	}
	
	public void updateCandidat(int cin, Candidat c) {
        Candidat old = cRepository.getByCin(cin);
        if (old == null) {
            System.out.println("Candidate not found!");
            return;
        }

        // Merge sessions
        Seance[] updatedSeances = c.getSeances() != null ? c.getSeances() : old.getSeances();

        // Recompute number of sessions
        int nbCode = 0;
        int nbConduite = 0;
        if (updatedSeances != null) {
            for (Seance s : updatedSeances) {
                if (s != null) {
                    if (s.getType().equalsIgnoreCase("Code")) nbCode++;
                    else if (s.getType().equalsIgnoreCase("Conduite")) nbConduite++;
                }
            }
        }

        // Create updated candidate object
        Candidat updated = new Candidat(
                c.getNom(),
                c.getPrenom(),
                c.getAdresse(),
                c.getTelephone(),
                c.getCin(),
                c.getTypePermis(),
                nbCode,
                nbConduite,
                c.getTotalPrice(),
                c.getPaidAmount(),
                updatedSeances
        );

        cRepository.update(cin, updated);
        System.out.println("Candidate updated successfully!");
    }
	public Candidat getByCin(int cin) {
	    return cRepository.getByCin(cin);
	}
	public void deleteCandidat(int cin) {
		cRepository.delete(cin);
	}
	
	public void findByCin(int cin) {
		cRepository.findByCin(cin);
	}
}
