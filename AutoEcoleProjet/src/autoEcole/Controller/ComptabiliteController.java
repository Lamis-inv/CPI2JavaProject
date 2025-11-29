package autoEcole.Controller;

import autoEcole.Entities.Comptabilite;

public class ComptabiliteController {
    private final Comptabilite comptabilite = new Comptabilite();

    public void enregistrerPaiement(double montant) {
        comptabilite.ajouterRevenu(montant);
    }

    public void enregistrerDepense(double montant) {
        comptabilite.ajouterDepense(montant);
    }

    public Comptabilite getComptabilite() {
        return comptabilite;
    }
}
