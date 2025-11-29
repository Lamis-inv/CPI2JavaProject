package autoEcole.Controller;

import autoEcole.Entities.Vehicule;
import autoEcole.Entities.Maintenance;
import autoEcole.Entities.Reparation;
import autoEcole.Service.VehiculeService;
import autoEcole.UI.VehiculeUI;

public class VehiculeController {
    private final VehiculeService service = new VehiculeService();
    private final VehiculeUI ui = new VehiculeUI(this);
    
    public void init() {
    	ui.lancerMenu();
    }
    public void ajouterVehicule(Vehicule v) {
    	service.ajouterVehicule(v); 
    	}
    public void modifierVehicule(String imm, Vehicule v) {
    	service.modifierVehicule(imm, v); 
    	}
    public void supprimerVehicule(String imm) {
    	service.supprimerVehicule(imm); 
    	}
    public Vehicule[] getTous() {
    	return service.getTous(); 
    	}
    public void afficherEtatVehicule(String imm) {
    	service.afficherEtatVehicule(imm); 
    	}

    public void ajouterMaintenance(Maintenance m) {
    	service.ajouterMaintenance(m); 
    	}
    public void ajouterReparation(Reparation r) {
    	service.ajouterReparation(r); 
    	}
	public Vehicule getVehicule(String imm) {
		service.getVehicule(imm);
		return null;
	}
}
