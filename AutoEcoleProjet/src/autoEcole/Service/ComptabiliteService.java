package autoEcole.Service;

import autoEcole.Entities.*;
import autoEcole.Repository.*;

import java.time.LocalDate;

public class ComptabiliteService {

    private final CandidatRepository cRepo;
    private final MoniteurRepository mRepo;
    private final VehiculeRepository vRepo;
    private final ReparationRepository rRepo;
    private final MaintenanceRepository maRepo;
    private final SeanceRepository sRepo;

    public ComptabiliteService(
            CandidatRepository cRepo,
            MoniteurRepository mRepo,
            VehiculeRepository vRepo,
            ReparationRepository rRepo,
            MaintenanceRepository maRepo,
            SeanceRepository sRepo
    ) {
        this.cRepo = cRepo;
        this.mRepo = mRepo;
        this.vRepo = vRepo;
        this.rRepo = rRepo;
        this.maRepo = maRepo;
        this.sRepo = sRepo;
    }

    // === Record a candidate payment for a session ===
    public void enregistrerSeancePayment(Seance s) {
        Candidat c = s.getCandidat();
        if (c != null) {
            c.pay(s.getPrix());
            cRepo.update(c.getCin(), c);
        }
    }

    // === Pay a moniteur for worked hours ===
    public void payerMoniteur(Moniteur m, double heures, double tauxHoraire) {
        double salaire = heures * tauxHoraire;
        m.setSalaireRecu(m.getSalaireRecu() + salaire);
        mRepo.update(m.getId(), m);
    }

    // === Register vehicle maintenance expense ===
    public void enregistrerMaintenance(Maintenance m) {
        maRepo.ajouter(m);
    }

    // === Register vehicle reparation expense ===
    public void enregistrerReparation(Reparation r) {
        rRepo.ajouter(r);
    }

    // === Compute and display monthly financial summary ===
    public void afficherMois(int mois, int annee) {
        double revenus = getTotalRevenus();
        double salaires = getTotalSalaires();
        double maintenances = getTotalMaintenance();
        double reparations = getTotalReparations();

        double depenses = salaires + maintenances + reparations;
        double profit = revenus - depenses;

        System.out.println("\n\nRevenus (Candidats) : " + revenus + " dt");
        System.out.println("Salaires Moniteurs   : " + salaires + " dt");
        System.out.println("Maintenance Vehicules: " + maintenances + " dt");
        System.out.println("Réparations Vehicules: " + reparations + " dt");
        System.out.println(">>> Profit: " + profit + " dt");

    }
    public double getTotalMaintenance() {
        double total = 0;
        for (Maintenance m : maRepo.getAll()) {
            total += m.getCout();
        }
        return total;
    }

    public double getTotalReparations() {
        double total = 0;
        for (Reparation r : rRepo.getAll()) {
            total += r.getCout();
        }
        return total;
    }
    public double getTotalSalaires() {
        double total = 0;
        for (Moniteur m : mRepo.getAll()) {
            total += m.getSalaireBase(); // base
            total += m.getNbHeuresTravaillees() * m.getPrixParHeure(); // extra per hour
        }
        return total;
    }
    public double getTotalRevenus() {
        double total = 0;
        for (Candidat c : cRepo.getAll()) {
            total += c.getPaidAmount();
        }
        return total;
    }


}
