package autoEcole.Controller;

import autoEcole.Entities.Candidat;
import autoEcole.Service.CandidatService;
import autoEcole.UI.CandidatUI;

public class CandidatController {
	
	CandidatService candidatService = new CandidatService();
	CandidatUI candidatUI = new CandidatUI();
	
	public void init() {
		Candidat c =CandidatUI.saisir();
		candidatService.addCandidat(c);
	}
}
