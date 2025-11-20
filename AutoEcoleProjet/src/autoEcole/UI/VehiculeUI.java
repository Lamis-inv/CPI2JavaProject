package autoEcole.UI;

import java.util.Scanner;

import autoEcole.Controller.VehiculeController;
import autoEcole.Entities.Vehicule;

public class VehiculeUI {

    VehiculeController vcontroller = new VehiculeController();

    public void init() {
        boolean test = true;
        Scanner scanner = new Scanner(System.in);

        while (test) {
            System.out.println("===== Vehicule Menu =====");
            System.out.println("1. Add Vehicule");
            System.out.println("2. Show All Vehicules");
            System.out.println("3. Update Vehicule");
            System.out.println("4. Delete Vehicule");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1 -> saisir();
                case 2 -> vcontroller.findAll();
                case 3 -> updateVehicule();
                case 4 -> deleteVehicule();
                case 0 -> test = false;
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    public void saisir() {
        Scanner scanner = new Scanner(System.in);
        
        String imm,type,d;
        int kiloTotal,kiloAvant;
        
        System.out.print("Immatricule: ");
        imm = scanner.nextLine();
                
        System.out.print("Type: ");
        type = scanner.nextLine();
        
        System.out.print("Date mise en service: ");
        d = scanner.nextLine();

        System.out.print("Kilometrage total: ");
        kiloTotal = Integer.parseInt(scanner.nextLine());

        System.out.print("Km avant entretien: ");
        kiloAvant = Integer.parseInt(scanner.nextLine());

        vcontroller.add(new Vehicule(imm, d, kiloTotal, kiloAvant, type));
    }

    public void deleteVehicule() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter immatricule to delete: ");
        String imm = scanner.nextLine();
        vcontroller.delete(imm);
    }

    public void updateVehicule() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter immatricule to update: ");
        String imm = scanner.nextLine();

        System.out.println("Enter NEW values:");

        System.out.print("Type: ");
        String type = scanner.nextLine();

        System.out.print("Date mise en service: ");
        String d = scanner.nextLine();

        System.out.print("Kilometrage total: ");
        int kiloTotal = Integer.parseInt(scanner.nextLine());

        System.out.print("Km avant entretien: ");
        int kiloAvant = Integer.parseInt(scanner.nextLine());

        Vehicule v = new Vehicule(imm, d, kiloTotal, kiloAvant, type);

        vcontroller.update(imm, v);
    }
}
