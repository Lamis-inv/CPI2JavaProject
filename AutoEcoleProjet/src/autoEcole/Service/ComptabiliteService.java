package autoEcole.Service;

import autoEcole.Entities.Comptabilite;
import autoEcole.Repository.ComptabiliteRepository;

import java.time.LocalDate;

public class ComptabiliteService {

    private final ComptabiliteRepository repo;

    public ComptabiliteService(ComptabiliteRepository repo) {
        this.repo = repo;
    }

    /**
     * Get global totals: total revenus, depenses, benefice
     */
    public Comptabilite getTotals() {
        Comptabilite total = new Comptabilite();

        for (Comptabilite c : repo.getAll()) {
            if (c.getType().equals("revenu")) {
                total.ajouterRevenu(c.getMontant());
            } else {
                total.ajouterDepense(c.getMontant());
            }
        }
        return total;
    }

    /**
     * Record money received from ANY candidate
     */
    public void enregistrerRevenu(double montant) {
        repo.add(new Comptabilite(
                "revenu",
                "candidat",
                montant,
                LocalDate.now()
        ));
    }

    /**
     * Generic expense: repairs, maintenance, equipment, etc.
     */
    public void enregistrerDepense(String categorie, double montant, LocalDate date) {
        repo.add(new Comptabilite(
                "depense",
                categorie,
                montant,
                date
        ));
    }

    /**
     * Salary paid to a moniteur
     */
    public void salaireMoniteur(int idMoniteur, double montant) {
        repo.add(new Comptabilite(
                "depense",
                "salaire_moniteur_" + idMoniteur,
                montant,
                LocalDate.now()
        ));
    }

    /**
     * Get the full accounting list
     */
    public Comptabilite[] getAll() {
        return repo.getAll();
    }

    /**
     * Show one month’s accounting
     */
    public void afficherMois(int mois, int annee) {

        Comptabilite[] tab = repo.getAll();

        double totalRevenus = 0;
        double totalDepenses = 0;

        System.out.println("\n===== Comptabilité du mois " + mois + "/" + annee + " =====");

        for (Comptabilite c : tab) {

            if (c.getDate().getMonthValue() == mois &&
                c.getDate().getYear() == annee) {

                System.out.println(
                        c.getDate() + " | " +
                        c.getType() + " | " +
                        c.getCategorie() + " | " +
                        c.getMontant() + " dt"
                );

                if (c.getType().equals("revenu")) {
                    totalRevenus += c.getMontant();
                } else {
                    totalDepenses += c.getMontant();
                }
            }
        }

        System.out.println("\nTotal revenus : " + totalRevenus + " dt");
        System.out.println("Total dépenses : " + totalDepenses + " dt");
        System.out.println(">>> Profit : " + (totalRevenus - totalDepenses) + " dt");
    }
}
