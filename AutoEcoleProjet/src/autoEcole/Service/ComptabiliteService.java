package autoEcole.Service;

import autoEcole.Entities.Comptabilite;
import autoEcole.Repository.ComptabiliteRepository;
import java.time.LocalDate;

public class ComptabiliteService {

    private final ComptabiliteRepository repo;

    public ComptabiliteService(ComptabiliteRepository repo) {
        this.repo = repo;
    }

    public void enregistrerRevenu(double montant) {
        repo.add(new Comptabilite("revenu", "candidat", montant, LocalDate.now()));
    }

    public void enregistrerDepense(String categorie, double montant, LocalDate date) {
        repo.add(new Comptabilite("depense", categorie, montant, date));
    }

    public void salaireMoniteur(int idMoniteur, double montant) {
        repo.add(new Comptabilite(
                "depense",
                "salaire",
                montant,
                LocalDate.now()
        ));
    }

    public Comptabilite[] getAll() {
        return repo.getAll();
    }

    public void afficherMois(int mois, int annee) {
        Comptabilite[] tab = repo.getAll();

        double totalRevenus = 0;
        double totalDepenses = 0;

        System.out.println("\n===== Comptabilité du mois " + mois + "/" + annee + " =====");

        for (Comptabilite c : tab) {
            if (c.getDate().getMonthValue() == mois && c.getDate().getYear() == annee) {

                System.out.println(
                    c.getDate() + " | " + c.getType() + " | " + c.getCategorie()
                    + " | " + c.getMontant() + " dt"
                );

                if (c.getType().equals("revenu")) totalRevenus += c.getMontant();
                else totalDepenses += c.getMontant();
            }
        }

        System.out.println("\nTotal revenus : " + totalRevenus + " dt");
        System.out.println("Total dépenses : " + totalDepenses + " dt");
        System.out.println(">>> Profit : " + (totalRevenus - totalDepenses) + " dt");
    }
}
