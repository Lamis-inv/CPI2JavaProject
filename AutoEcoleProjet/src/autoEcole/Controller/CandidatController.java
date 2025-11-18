package autoEcole.Controller;

import autoEcole.Entities.Candidat;
import autoEcole.Service.CandidatService;
import autoEcole.UI.CandidatUI;

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
	public void delete(int cin) {
		candidatService.deleteCandidat(cin);
	}
}
