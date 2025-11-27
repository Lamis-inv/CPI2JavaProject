package autoEcole.Controller;

import autoEcole.Entities.Vehicule;
import autoEcole.Entities.Maintenance;
import autoEcole.Entities.Reparation;
import autoEcole.Service.VehiculeService;

public class VehiculeController {
    private final VehiculeService service = new VehiculeService();

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
}
