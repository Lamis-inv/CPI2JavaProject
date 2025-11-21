package autoEcole.UI;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

import autoEcole.Controller.CandidatController;
import autoEcole.Controller.SeanceController;
import autoEcole.Entities.Candidat;
import autoEcole.Entities.Moniteur;
import autoEcole.Entities.Seance;
import autoEcole.Entities.Vehicule;
import autoEcole.Repository.CandidatRepository;
import autoEcole.Repository.MoniteurRepository;
import autoEcole.Repository.VehiculeRepository;

public class SeanceUI {

    private final SeanceController controller = new SeanceController();
    private final Scanner scanner = new Scanner(System.in);

    // repos to pick linked objects
    private final MoniteurRepository moniteurRepo = new MoniteurRepository();
    private final CandidatRepository candidatRepo = new CandidatRepository();
    private final VehiculeRepository vehiculeRepo = new VehiculeRepository();

    public void init() {

        boolean loop = true;

        while (loop) {
            System.out.println("\n===== Séance Menu =====");
            System.out.println("1. Add Séance");
            System.out.println("2. Show All Séances");
            System.out.println("3. Find By ID");
            System.out.println("4. Update Séance");
            System.out.println("5. Delete Séance");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            int choice = getIntInput();

            switch (choice) {
                case 1 -> saisir();
                case 2 -> controller.findAll();
                case 3 -> findById();
                case 4 -> updateSeance();
                case 5 -> deleteSeance();
                case 0 -> loop = false;
                default -> System.out.println("Invalid choice!");
            }
        }
    }


    private int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid number. Try again: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private double getDoubleInput() {
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid number. Try again: ");
            scanner.next();
        }
        return scanner.nextDouble();
    }


    public void saisir() {
        scanner.nextLine(); // clean \n

        System.out.print("Enter ID: ");
        int id = getIntInput();
        scanner.nextLine();

        String type = "";
        while (true) {
            System.out.println("Choose Seance Type:");
            System.out.println("1. Conduite");
            System.out.println("2. Code");
            System.out.print("Your choice: ");

            int ch = scanner.nextInt();
            scanner.nextLine(); // clear buffer

            switch (ch) {
                case 1:
                    type = "conduite";
                    break;
                case 2:
                    type = "code";
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    continue;
            }
            break;
        }

        System.out.print("Enter date (YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(scanner.nextLine());

        System.out.print("Enter heure (HH:MM): ");
        LocalTime heure = LocalTime.parse(scanner.nextLine());

        double prix;
        if(type.equals("code")) {
            prix = 50;
        } else { // conduite
            prix = 100;
        }

        // --- Choose linked Moniteur ---
        Moniteur moniteur = chooseMoniteur();

        // --- Choose linked Candidat ---
        Candidat candidat = chooseCandidat(type,prix);

        // --- Choose linked Vehicle ---
        Vehicule vehicule = chooseVehicule(type);

        Seance s = new Seance(id, type,date, heure, moniteur, candidat, prix, vehicule);
        controller.add(s);
        
    }


    private Candidat chooseCandidat(String type,double prix) {
        Candidat[] all = candidatRepo.getAll();
        if (all.length == 0) {
            System.out.println("No candidats available!");
            return null;
        }
        System.out.println("\n--- Choose Candidat ---");
        int i;
        for (i=0; i < all.length; i++) {
            Candidat c = all[i];
            System.out.println((i + 1) + ") " + c.getNom() + " " + c.getPrenom());
        }
        
        
        System.out.print("Choose: ");
        int index = getIntInput();
        Candidat c = all[index - 1];
        if (type.equalsIgnoreCase("code")) {
            c.setNbSeanceCode(c.getNbSeanceCode() + 1);
        } else if (type.equalsIgnoreCase("conduite")) {
            c.setNbSeanceConduite(c.getNbSeanceConduite() + 1);
        }
        CandidatController candidatController = new CandidatController();
        candidatController.update(c.getCin(), c);
        return c;
    }
    

    	private Moniteur chooseMoniteur() {
    	    Moniteur[] all = moniteurRepo.getAll();

    	    // Count available moniteurs
    	    int count = 0;
    	    for (Moniteur m : all) {
    	        if (m.isDisponible()) count++;
    	    }

    	    if (count == 0) {
    	        System.out.println("No moniteurs available!");
    	        return null;
    	    }

    	    Moniteur[] available = new Moniteur[count];
    	    int idx = 0;
    	    for (Moniteur m : all) {
    	        if (m.isDisponible()) available[idx++] = m;
    	    }

    	    // Show options
    	    System.out.println("\n--- Choose Moniteur ---");
    	    for (int i = 0; i < available.length; i++) {
    	        System.out.println((i + 1) + ") " + available[i].getNom() + " (ID " + available[i].getId() + ")");
    	    }

    	    Moniteur moniteur = null;
    	    while (moniteur == null) {
    	        int choice = getIntInput();
    	        if (choice >= 1 && choice <= available.length) {
    	            moniteur = available[choice - 1];
    	            moniteur.setDisponible(false);
    	            moniteur.setNbHeuresTravaillees(moniteur.getNbHeuresTravaillees()+1);
    	            moniteurRepo.update(moniteur.getId(), moniteur);
    	        } else {
    	            System.out.print("Invalid choice. Choose again: ");
    	        }
    	    }
    	    return moniteur;
    	}

    	private Vehicule chooseVehicule(String type) {
    	    if (!type.equals("conduite")) return null; // only for conduite

    	    Vehicule[] all = vehiculeRepo.getAll();
    	    if (all.length == 0) {
    	        System.out.println("No vehicules available!");
    	        return null;
    	    }

    	    System.out.println("\n--- Choose Vehicule ---");
    	    for (int i = 0; i < all.length; i++) {
    	        System.out.println((i + 1) + ") " + all[i].getNumImmatricule());
    	    }

    	    Vehicule vehicule = null;
    	    while (vehicule == null) {
    	        int choice = getIntInput();
    	        if (choice >= 1 && choice <= all.length) {
    	            vehicule = all[choice - 1];
    	        } else {
    	            System.out.print("Invalid choice. Choose again: ");
    	        }
    	    }
    	    return vehicule;
    	}
  

    private void deleteSeance() {
        System.out.print("Enter ID: ");
        controller.delete(getIntInput());
    }

    private void findById() {
        System.out.print("Enter ID: ");
        controller.findById(getIntInput());
    }

    private void updateSeance() {
        System.out.print("Enter ID of the Seance to update: ");
        int id = getIntInput();
        Seance old = controller.getById(id);

        if (old == null) {
            System.out.println("Seance not found!");
            return;
        }

        boolean loop = true;
        scanner.nextLine(); // consume newline

        while (loop) {
            System.out.println("\n===== Update Seance Menu =====");
            System.out.println("1. Update Type");
            System.out.println("2. Update Date");
            System.out.println("3. Update Heure");
            System.out.println("4. Update Moniteur");
            System.out.println("5. Update Candidat");
            System.out.println("6. Update Vehicule");
            System.out.println("7. Update Prix");
            System.out.println("0. Save & Exit");
            System.out.print("Choose an option: ");

            int choice = getIntInput();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    System.out.println("Choose Seance Type:");
                    System.out.println("1. Conduite");
                    System.out.println("2. Code");
                    int ch = getIntInput();
                    scanner.nextLine();
                    if (ch == 1) {
                        old.setType("conduite");
                        old.setPrix(100); // auto set price
                    } else if (ch == 2) {
                        old.setType("code");
                        old.setPrix(50); // auto set price
                    } else {
                        System.out.println("Invalid choice!");
                    }
                }
                case 2 -> {
                    System.out.print("Enter new date (YYYY-MM-DD): ");
                    old.setDate(LocalDate.parse(scanner.nextLine()));
                }
                case 3 -> {
                    System.out.print("Enter new heure (HH:MM): ");
                    old.setHeure(LocalTime.parse(scanner.nextLine()));
                }
                case 4 -> old.setMoniteur(chooseMoniteur());
                case 5 -> old.setCandidat(chooseCandidat(old.getType(),old.getPrix()));
                case 6 -> old.setVehicule(chooseVehicule(old.getType()));
                case 7 -> {
                    System.out.print("Enter new prix: ");
                    old.setPrix(getDoubleInput());
                    scanner.nextLine();
                }
                case 0 -> {
                    controller.update(id, old);
                    System.out.println("Seance updated successfully!");
                    loop = false;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

}
