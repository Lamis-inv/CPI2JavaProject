package autoEcole.UI;

import autoEcole.Controller.ComptabiliteController;
import autoEcole.Entities.Comptabilite;

public class ComptabiliteUI {
    private final ComptabiliteController controller;

    public ComptabiliteUI(ComptabiliteController controller) {
        this.controller = controller;
    }

    public void afficherComptabilite() {
        Comptabilite c = controller.getComptabilite();
        System.out.println("===== Suivi Comptable =====");
        System.out.println("Revenus: " + c.getRevenus());
        System.out.println("Dépenses: " + c.getDepenses());
        System.out.println("Bénéfice: " + c.getBenefice());
    }
}
