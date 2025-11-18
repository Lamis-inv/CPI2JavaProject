package autoEcole.Service;

import java.util.List;

import autoEcole.Entities.Candidat;
import autoEcole.Repository.CandidatRepository;

public class CandidatService {
	CandidatRepository cRepository = new CandidatRepository();
	
	public void addCandidat(Candidat c) {
		Candidat can = new Candidat(c.getNom(), c.getPrenom(), c.getAdresse(), c.getTelephone(), c.getCin(), c.getTypePermis(), c.getNbSeanceCode(), c.getNbSeanceConduite());
		cRepository.add(can);
	}
	
	public void findAllCandidat() {
		 cRepository.findAll();
	}
	
	public void updateCandidat(Candidat c) {
		cRepository.update(null, c);
	}
}
