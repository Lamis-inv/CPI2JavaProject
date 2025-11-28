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
        if (v == null) { 
            System.out.println("Véhicule non trouvé !");
            return; 
        }

        System.out.println("\n===== Rapport sur le véhicule =====");
        System.out.println("Immatriculation : " + v.getNumImmatricule());
        System.out.println("Type : " + v.getType());
        System.out.println("Kilométrage total : " + v.getKilometrageTotal());

        System.out.println("\n--- État administratif et technique ---");

        alerteVignette(v.getVignetteDerniereDate());
        alerteAssurance(v.getAssuranceDerniereDate());
        alerteVisiteTechnique(v.getVisiteTechniqueDerniereDate(), v.getDateMiseEnService());
        alerteVidange(v.getKmAvantEntretien());

        System.out.println("\n--- Historique des maintenances ---");
        maintenanceRepo.afficherParVehicule(imm);

        System.out.println("\n--- Historique des réparations ---");
        reparationRepo.afficherParVehicule(imm);
    }


    private void alerteVignette(LocalDate derniereDate) {
        int annee = derniereDate.getYear();
        LocalDate expiration = LocalDate.of(annee, 12, 31);
        LocalDate today = LocalDate.now();

        if (expiration.isBefore(today))
            System.out.println("Vignette : EXPIRÉE (depuis " + expiration + ")");
        else if (expiration.minusDays(30).isBefore(today))
            System.out.println("Vignette : Bientôt expirée (le " + expiration + ")");
        else
            System.out.println("Vignette : OK (expire le " + expiration + ")");
    }
    private void alerteAssurance(LocalDate derniereDate) {
        LocalDate expiration = derniereDate.plusYears(1);
        LocalDate today = LocalDate.now();

        if (expiration.isBefore(today))
            System.out.println("Assurance : EXPIRÉE (depuis " + expiration + ")");
        else if (expiration.minusDays(30).isBefore(today))
            System.out.println("Assurance : Bientôt expirée (le " + expiration + ")");
        else
            System.out.println("Assurance : OK (expire le " + expiration + ")");
    }
    private void alerteVisiteTechnique(LocalDate derniereDate, LocalDate dateMiseEnService) {
        LocalDate today = LocalDate.now();
        int age = today.getYear() - dateMiseEnService.getYear();

        LocalDate expiration;

        if (age < 3) {
            expiration = dateMiseEnService.plusYears(3);
        } else if (age < 10) {
            expiration = derniereDate.plusYears(2);
        } else {
            expiration = derniereDate.plusYears(1);
        }

        if (expiration.isBefore(today))
            System.out.println("Visite Technique : EXPIRÉE (depuis " + expiration + ")");
        else if (expiration.minusDays(30).isBefore(today))
            System.out.println("Visite Technique : Bientôt expirée (le " + expiration + ")");
        else
            System.out.println("Visite Technique : OK (expire le " + expiration + ")");
    }
    private void alerteVidange(int kmAvantEntretien) {
        if (kmAvantEntretien <= 0)
            System.out.println("Vidange : ENTRETIENT EN RETARD !");
        else if (kmAvantEntretien <= 200)
            System.out.println("Vidange : Bientôt nécessaire (reste " + kmAvantEntretien + " km)");
        else
            System.out.println("Vidange : OK (reste " + kmAvantEntretien + " km)");
    }


    public void ajouterMaintenance(Maintenance m) {
    	maintenanceRepo.ajouter(m); 
    	}
    public void ajouterReparation(Reparation r) {
    	reparationRepo.ajouter(r); 
    	}
}
