package autoEcole.Service;

import autoEcole.Entities.Maintenance;
import autoEcole.Entities.Reparation;
import autoEcole.Entities.Vehicule;
import autoEcole.Repository.VehiculeRepository;
import autoEcole.Repository.MaintenanceRepository;
import autoEcole.Repository.ReparationRepository;

import java.time.LocalDate;

public class VehiculeService {

    private final VehiculeRepository vehiculeRepo = new VehiculeRepository();
    private final MaintenanceRepository maintenanceRepo = new MaintenanceRepository();
    private final ReparationRepository reparationRepo = new ReparationRepository();

    public void ajouterVehicule(Vehicule v) {
    	vehiculeRepo.ajouter(v); 
    	}
    
    public void modifierVehicule(String imm, Vehicule v) {
    	vehiculeRepo.modifier(imm, v);
    	}
    
    public void supprimerVehicule(String imm) {
    	vehiculeRepo.supprimer(imm); 
    	}
    
    public Vehicule[] getTous() {
    	return vehiculeRepo.getAll(); 
    	}
    
    public Vehicule getVehicule(String imm) {
    	return vehiculeRepo.findByImmatriculation(imm); 
    	}

    public void afficherEtatVehicule(String imm) {
        Vehicule v = vehiculeRepo.findByImmatriculation(imm);
        if (v == null) { System.out.println("Vehicule non trouvé !"); return; }

        System.out.println("\n===== Rapport sur le véhicule =====");
        System.out.println("Immatriculation: " + v.getNumImmatricule());
        System.out.println("Type: " + v.getType());
        System.out.println("Kilométrage total: " + v.getKilometrageTotal());
        System.out.println("\n--- Échéances ---");
        alerte(v.getVignetteExpiration(), "Vignette");
        alerte(v.getAssuranceExpiration(), "Assurance");
        alerte(v.getVisiteTechniqueExpiration(), "Visite Technique");
        alerte(v.getVidangeExpiration(), "Vidange");

        System.out.println("\n--- Historique des maintenances ---");
        maintenanceRepo.afficherParVehicule(imm);
        System.out.println("\n--- Historique des réparations ---");
        reparationRepo.afficherParVehicule(imm);
    }

    private void alerte(LocalDate date, String label) {
        LocalDate today = LocalDate.now();
        if (date.isBefore(today)) System.out.println(label + ": EXPIRÉ (" + date + ")");
        else if (date.minusDays(15).isBefore(today)) System.out.println(label + ": Bientôt expiré (" + date + ")");
        else System.out.println(label + ": OK (" + date + ")");
    }

    public void ajouterMaintenance(Maintenance m) {
    	maintenanceRepo.ajouter(m); 
    	}
    public void ajouterReparation(Reparation r) {
    	reparationRepo.ajouter(r); 
    	}
}
