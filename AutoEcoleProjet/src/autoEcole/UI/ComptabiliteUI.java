package autoEcole.UI;

import autoEcole.Controller.ComptabiliteController;
import autoEcole.Entities.Comptabilite;
import java.util.Scanner;

public class ComptabiliteUI {

    private final ComptabiliteController controller;

    public ComptabiliteUI(ComptabiliteController controller) {
        this.controller = controller;
    }

    public void menu() {
        Scanner sc = new Scanner(System.in);

        int choix;
        do {
            System.out.println("\n===== Comptabilité =====");
            System.out.println("1. Enregistrer un revenu");
            System.out.println("2. Enregistrer une dépense");
            System.out.println("3. Afficher mois");
            System.out.println("4. Afficher total");
            System.out.println("0. Retour");
            choix = sc.nextInt();

            switch (choix) {
                case 1:
                    System.out.print("Montant revenu: ");
                    controller.enregistrerRevenu(sc.nextDouble());
                    break;

                case 2:
                    System.out.print("Montant dépense: ");
                    double m = sc.nextDouble();
                    controller.enregistrerDepense("Autre", m, java.time.LocalDate.now());
                    break;

                case 3:
                    System.out.print("Mois: ");
                    int mois = sc.nextInt();
                    System.out.print("Année: ");
                    int an = sc.nextInt();
                    controller.afficherMois(mois, an);
                    break;

                case 4:
                    Comptabilite total = controller.service.getTotals();
                    System.out.println("Revenus: " + total.getRevenus());
                    System.out.println("Dépenses: " + total.getDepenses());
                    System.out.println("Bénéfice: " + total.getBenefice());
                    break;
            }

        } while (choix != 0);
    }
}
