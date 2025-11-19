package autoEcole.Controller;

import autoEcole.Entities.Candidat;
import autoEcole.Service.CandidatService;


public class CandidatController {
	
	CandidatService candidatService = new CandidatService();
	
	public void add(Candidat c) {
		candidatService.addCandidat(c);
	}
	public void findAll() {
		candidatService.findAllCandidat();
	}
	public void update(int cin,Candidat c) {
		candidatService.updateCandidat(cin,c);
	}
	public Candidat getByCin(int cin) {
	    return candidatService.getByCin(cin);
	}
	public void delete(int cin) {
		candidatService.deleteCandidat(cin);
	}
	public void findByCin(int cin) {
		candidatService.findByCin(cin);
	}
}
