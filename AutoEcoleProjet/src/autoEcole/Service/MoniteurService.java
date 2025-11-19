package autoEcole.Service;


import autoEcole.Entities.Moniteur;
import autoEcole.Repository.MoniteurRepository;

public class MoniteurService {
MoniteurRepository mRepository = new MoniteurRepository();
	
	public void addMoniteur(Moniteur c) {
		Moniteur can = new Moniteur(c.getId(), c.getNom(),c.isDisponible(),c.getNbHeuresTravaillees());
		mRepository.add(can);
	}
	
	public void findAllCandidat() {
		mRepository.findAll();
	}
	
	public void updateCandidat(int cin,Moniteur c) {
		mRepository.update(cin, c);
	}
	public Moniteur getByCin(int cin) {
	    return mRepository.getById(cin);
	}
	public void deleteCandidat(int cin) {
		mRepository.delete(cin);
	}
	
	public void findByCin(int cin) {
		mRepository.findById(cin);
	}
}
