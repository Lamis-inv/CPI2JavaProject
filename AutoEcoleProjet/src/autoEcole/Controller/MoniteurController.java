package autoEcole.Controller;

import autoEcole.Entities.Moniteur;
import autoEcole.Service.MoniteurService;

public class MoniteurController {

MoniteurService moniteurService = new MoniteurService();
	
	public void add(Moniteur c) {
		moniteurService.addMoniteur(c);
	}
	public void findAll() {
		moniteurService.findAllCandidat();
	}
	public void update(int id,Moniteur c) {
		moniteurService.updateCandidat(id,c);
	}
	public Moniteur getById(int id) {
	    return moniteurService.getByCin(id);
	}
	public void delete(int id) {
		moniteurService.deleteCandidat(id);
	}
	public void findByCin(int id) {
		moniteurService.findByCin(id);
	}
}
