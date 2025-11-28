package autoEcole.UI;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import autoEcole.Controller.VehiculeController;
import autoEcole.Entities.Vehicule;
import autoEcole.Entities.Maintenance;
import autoEcole.Entities.Reparation;

public class VehiculeUI {

    private final VehiculeController controller = new VehiculeController();
    private final Scanner scanner = new Scanner(System.in);

    public void lancerMenu() {
        boolean continuer = true;
        while (continuer) {
            System.out.println("\n===== Menu Véhicule =====");
            System.out.println("1. Ajouter un véhicule");
            System.out.println("2. Afficher tous les véhicules");
            System.out.println("3. Modifier un véhicule");
            System.out.println("4. Supprimer un véhicule");
            System.out.println("5. Afficher état d'un véhicule");
            System.out.println("6. Ajouter maintenance");
            System.out.println("7. Ajouter réparation");
            System.out.println("0. Quitter");
            System.out.print("Choisissez une option : ");

            int choix = lireInt();
            switch (choix) {
                case 1 -> ajouterVehicule();
                case 2 -> afficherTous();
                case 3 -> modifierVehicule();
                case 4 -> supprimerVehicule();
                case 5 -> afficherEtatVehicule();
                case 6 -> ajouterMaintenance();
                case 7 -> ajouterReparation();
                case 0 -> continuer = false;
                default -> System.out.println("Choix invalide !");
            }
        }
    }

    private void ajouterVehicule() {
        System.out.println("\n--- Ajouter une nouvelle Véhicule ---");

        String imm;
        do {
            System.out.print("Immatricule : ");
            imm = scanner.nextLine().trim();

            if (!imm.matches("\\d{1,3}TUN\\d{1,4}")) {
                System.out.println("Immatricule invalide !");
            }
        } while (!imm.matches("\\d{1,3}TUN\\d{1,4}"));
        
        String type;
        do {
            System.out.print("Type de la vehicule: ");
            type = scanner.nextLine().trim().toLowerCase();
            if (!type.matches("moto|voiture|camion|autobus")) {
                System.out.println("Type invalide !");
            }
        } while (!type.matches("moto|voiture|camion|autobus"));
        
        LocalDate dateMiseEnService = lireDate("Date mise en service (YYYY-MM-DD) : ");
        int kilometrageTotal = lireInt("Kilométrage total : ");
        int kmAvantEntretien = lireInt("Km avant entretien : ");

        LocalDate vignette = lireDate("Échéance vignette (YYYY-MM-DD) : ");
        LocalDate assurance = lireDate("Échéance assurance (YYYY-MM-DD) : ");
        LocalDate visiteTech = lireDate("Échéance visite technique (YYYY-MM-DD) : ");
        LocalDate vidange = lireDate("Échéance vidange (YYYY-MM-DD) : ");

        Vehicule v = new Vehicule(imm, dateMiseEnService, kilometrageTotal, kmAvantEntretien, type,
                vignette, assurance, visiteTech, vidange);

        controller.ajouterVehicule(v);
    }

    private void afficherTous() {
        System.out.println("\n--- Liste des véhicules ---");
        Vehicule[] vehicules = controller.getTous();
        if (vehicules.length == 0) { System.out.println("Aucun véhicule trouvé."); return; }
        for (Vehicule v : vehicules) {
            System.out.println("Immatriculation : " + v.getNumImmatricule() +
                    " | Type : " + v.getType() +
                    " | Kilométrage : " + v.getKilometrageTotal());
        }
    }

    private void modifierVehicule() {
        System.out.print("\nImmatriculation du véhicule à modifier : ");
        String imm = scanner.nextLine();
        Vehicule v = controller.getTous() == null ? null : controller.getTous()[0]; // retrieval
        if (v == null) { System.out.println("Véhicule non trouvé !"); return; }

        System.out.println("Saisir les nouvelles informations :");
        System.out.print("Type : ");
        String type = scanner.nextLine();
        LocalDate dateMiseEnService = lireDate("Date mise en service (YYYY-MM-DD) : ");
        int kilometrageTotal = lireInt("Kilométrage total : ");
        int kmAvantEntretien = lireInt("Km avant entretien : ");
        LocalDate vignette = lireDate("Échéance vignette (YYYY-MM-DD) : ");
        LocalDate assurance = lireDate("Échéance assurance (YYYY-MM-DD) : ");
        LocalDate visiteTech = lireDate("Échéance visite technique (YYYY-MM-DD) : ");
        LocalDate vidange = lireDate("Échéance vidange (YYYY-MM-DD) : ");

        Vehicule updated = new Vehicule(imm, dateMiseEnService, kilometrageTotal, kmAvantEntretien, type,
                vignette, assurance, visiteTech, vidange);

        controller.modifierVehicule(imm, updated);
    }

    private void supprimerVehicule() {
        System.out.print("\nImmatriculation du véhicule à supprimer : ");
        String imm = scanner.nextLine();
        controller.supprimerVehicule(imm);
    }

    private void afficherEtatVehicule() {
        System.out.print("\nImmatriculation du véhicule : ");
        String imm = scanner.nextLine();
        controller.afficherEtatVehicule(imm);
    }

    private void ajouterMaintenance() {
        System.out.print("\nImmatriculation du véhicule : ");
        String imm = scanner.nextLine();
        System.out.print("Description : ");
        String desc = scanner.nextLine();
        LocalDate date = lireDate("Date (YYYY-MM-DD) : ");
        double cout = lireDouble("Coût : ");
        Maintenance m = new Maintenance(imm, desc, date, cout);
        controller.ajouterMaintenance(m);
    }

    private void ajouterReparation() {
        System.out.print("\nImmatriculation du véhicule : ");
        String imm = scanner.nextLine();
        System.out.print("Description : ");
        String desc = scanner.nextLine();
        LocalDate date = lireDate("Date (YYYY-MM-DD) : ");
        double cout = lireDouble("Coût : ");
        Reparation r = new Reparation(imm, desc, date, cout);
        controller.ajouterReparation(r);
    }

    // verif date
    private LocalDate lireDate(String message) {
    	LocalDate date = null;
        boolean valide = false;

        while (!valide) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            try {
                // Try parsing using ISO format (YYYY-MM-DD)
                date = LocalDate.parse(input);

                // Check if the date is in the future
                if (date.isAfter(LocalDate.now())) {
                    System.out.println("La date ne peut pas être supérieure à aujourd'hui !");
                } else {
                    valide = true;
                }

            } catch (DateTimeParseException e) {
                System.out.println("Format invalide ! Format attendu : YYYY-MM-DD");
            }
        }
        return date;
    }

    //verif int and double
    private int lireInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try { return Integer.parseInt(scanner.nextLine()); }
            catch (NumberFormatException e) { System.out.println("Entier invalide. Réessayez."); }
        }
    }

    private int lireInt() { return lireInt(""); }

    private double lireDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try { return Double.parseDouble(scanner.nextLine()); }
            catch (NumberFormatException e) { System.out.println("Nombre invalide. Réessayez."); }
        }
    }
}
