package autoEcole.Controller;

import autoEcole.Service.ComptabiliteService;
import autoEcole.Entities.Comptabilite;
import java.time.LocalDate;

public class ComptabiliteController {

    public final ComptabiliteService service;

    public ComptabiliteController(ComptabiliteService service) {
        this.service = service;
    }

    public void enregistrerRevenu(double montant) {
        service.enregistrerRevenu(montant);
    }

    public void enregistrerDepense(String categorie, double montant, LocalDate date) {
        service.enregistrerDepense(categorie, montant, date);
    }

    public void salaireMoniteur(int idMoniteur, double montant) {
        service.salaireMoniteur(idMoniteur, montant);
    }

    public Comptabilite[] getAll() {
        return service.getAll();
    }

    public void afficherMois(int mois, int annee) {
        service.afficherMois(mois, annee);
    }
}
