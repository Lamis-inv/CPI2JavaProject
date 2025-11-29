package autoEcole.UI;

import autoEcole.Controller.ComptabiliteController;

public class ComptabiliteUI {

    private final ComptabiliteController controller;

    public ComptabiliteUI(ComptabiliteController controller) {
        this.controller = controller;
    }

    public void menu() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        while (true) {
            System.out.println("\n===== Menu Comptabilité =====");
            System.out.println("1. Afficher le mois");
            System.out.println("0. Retour");
            System.out.print("Choix : ");
            int choice = scanner.nextInt();

            if (choice == 0) break;

            switch (choice) {
                case 1:
                    controller.init();
                    break;
                default:
                    System.out.println("Choix invalide !");
            }
        }
    }
}
