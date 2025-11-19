package autoEcole.Service;


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
	
	public void updateCandidat(int cin,Candidat c) {
		cRepository.update(cin, c);
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
