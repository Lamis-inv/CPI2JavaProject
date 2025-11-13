package autoEcole.Service;

import autoEcole.Entities.Candidat;
import autoEcole.Repository.CandidatRepository;

public class CandidatService {
	CandidatRepository cRepository = new CandidatRepository();
	
	public void addCandidat(Candidat c) {
		Candidat can = new Candidat(c.getNom(), c.getPrenom(), c.getAdresse(), c.getTelephone(), c.getCin(), c.getTypePermis(), c.getNbSeanceCode(), c.getNbSeanceConduite());
		cRepository.save(can);
	}
	
}
