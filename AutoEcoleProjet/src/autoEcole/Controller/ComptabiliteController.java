package autoEcole.Controller;

import autoEcole.Service.ComptabiliteService;
import autoEcole.Entities.*;

public class ComptabiliteController {

    private final ComptabiliteService service;

    public ComptabiliteController(ComptabiliteService service) {
        this.service = service;
    }

    // Candidate paid for a session
    public void seancePayee(Seance s) { 
        service.enregistrerSeancePayment(s); 
    }

    // Pay a moniteur
    public void payerMoniteur(Moniteur m, double heures, double tauxHoraire) {
        service.payerMoniteur(m, heures, tauxHoraire);
    }

    // Vehicle maintenance
    public void maintenanceVehicule(Maintenance m) {
        service.enregistrerMaintenance(m);
    }

    // Vehicle repair
    public void reparationVehicule(Reparation r) {
        service.enregistrerReparation(r);
    }

    // Show monthly report
    public void afficherMois(int mois, int annee) {
        service.afficherMois(mois, annee);
    }

    // Optionally: interactive menu for UI
    public void init() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Entrez le mois (1-12) : ");
        int mois = scanner.nextInt();
        System.out.print("Entrez l'année : ");
        int annee = scanner.nextInt();
        afficherMois(mois, annee);
    }
}
