package autoEcole.UI;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Scanner;

import autoEcole.Controller.CandidatController;
import autoEcole.Controller.SeanceController;
import autoEcole.Entities.Candidat;
import autoEcole.Entities.Moniteur;
import autoEcole.Entities.Seance;
import autoEcole.Entities.TypesPermit;
import autoEcole.Entities.Vehicule;
import autoEcole.Repository.CandidatRepository;
import autoEcole.Repository.MoniteurRepository;
import autoEcole.Repository.VehiculeRepository;

public class SeanceUI {
	private SeanceController controller;

	public SeanceUI(SeanceController controller) {
	    this.controller = controller;
	}
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
    private int generateSeanceId() {
        Seance[] all = controller.getAll();
        int maxId = 0;
        for (Seance s : all) {
            if (s != null && s.getId() > maxId) {
                maxId = s.getId();
            }
        }
        return maxId + 1; // next unique ID
    }


    public void saisir() {
        scanner.nextLine(); // clean newline

        int id = generateSeanceId();
        System.out.println("Generated Seance ID = " + id);

        // Choose type
        String type = "";
        while (true) {
            System.out.println("Choose Seance Type: \n1. Conduite\n2. Code");
            int ch = getIntInput();
            scanner.nextLine();
            if (ch == 1) type = "conduite";
            else if (ch == 2) type = "code";
            else { System.out.println("Invalid choice."); continue; }
            break;
        }

        // Pick date and time
        LocalDate date = getDateFromUser();
        LocalTime heure = getTimeFromUser(date);

        // Pick moniteur and candidat
        Moniteur moniteur = chooseMoniteur(date, heure, id);
        Candidat candidat = chooseCandidat(type, date, heure, id);

        // Pick vehicle only for conduite
        Vehicule vehicule = type.equals("conduite") ? chooseVehicule(type) : null;

        // Calculate price
        double prix = type.equals("code") ? candidat.getTypePermis().getPrixCode()
                                          : candidat.getTypePermis().getPrixConduite();

        // Create seance
        Seance s = new Seance(id, type, date, heure, moniteur, candidat, prix, vehicule);

        // Call service to handle rules
        try {
            controller.add(s); // controller -> service -> repository
            System.out.println(" Seance added successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println(" " + e.getMessage());
        }
    }


   
    private LocalDate getDateFromUser() {
        LocalDate date = null;
        while (date == null) {
            System.out.print("Enter date (YYYY-MM-DD): ");
            try {
                LocalDate d = LocalDate.parse(scanner.nextLine());
                if (d.isBefore(LocalDate.now())) {
                    System.out.println(" Date cannot be in the past.");
                } else {
                    date = d;
                }
            } catch (Exception e) {
                System.out.println(" Invalid date format.");
            }
        }
        return date;
    }

    private LocalTime getTimeFromUser(LocalDate date) {
        LocalTime heure = null;
        while (heure == null) {
            System.out.print("Enter time (HH:MM) between 08:00 and 18:00: ");
            try {
                LocalTime t = LocalTime.parse(scanner.nextLine());
                if (t.isBefore(LocalTime.of(8, 0)) || t.isAfter(LocalTime.of(18, 0))) {
                    System.out.println(" Time must be between 08:00 and 18:00.");
                } else if (date.equals(LocalDate.now()) && t.isBefore(LocalTime.now())) {
                    System.out.println(" Cannot schedule a past time today.");
                } else {
                    heure = t;
                }
            } catch (Exception e) {
                System.out.println(" Invalid time format.");
            }
        }
        return heure;
    }


    private Candidat chooseCandidat(String type, LocalDate date, LocalTime heure, int excludeId) {
        Candidat[] all = candidatRepo.getAll();
        if (all.length == 0) {
            System.out.println("No candidats available!");
            return null;
        }

        System.out.println("\n--- Choose Candidat ---");
        for (int i = 0; i < all.length; i++) {
            Candidat c = all[i];
            System.out.println((i + 1) + ") " + c.getNom() + " " + c.getPrenom());
        }

        System.out.print("Choose: ");
        int index = getIntInput();
        Candidat c = all[index - 1];

        candidatRepo.update(c.getCin(), c);
        return c;
    }



    private Moniteur chooseMoniteur(LocalDate date, LocalTime heure, int excludeId) {
        Moniteur[] all = moniteurRepo.getAll();
        Moniteur[] available = Arrays.stream(all)
            .filter(m -> !isMoniteurBusy(m, date, heure, excludeId))
            .toArray(Moniteur[]::new);

        if (available.length == 0) {
            System.out.println("No moniteurs available for this date/time!");
            return null;
        }

        System.out.println("\n--- Choose Moniteur ---");
        for (int i = 0; i < available.length; i++) {
            System.out.println((i + 1) + ") " + available[i].getNom() + " (ID " + available[i].getId() + ")");
        }

        Moniteur chosen = null;
        while (chosen == null) {
            int choice = getIntInput();
            if (choice >= 1 && choice <= available.length) {
                chosen = available[choice - 1];
                chosen.setNbHeuresTravaillees(chosen.getNbHeuresTravaillees() + 1);
                moniteurRepo.update(chosen.getId(), chosen);
            } else {
                System.out.print("Invalid choice. Choose again: ");
            }
        }

        return chosen;
    }


    private boolean isMoniteurBusy(Moniteur m, LocalDate date, LocalTime heure, int excludeId) {
        Seance[] all = controller.getAll();
        for (Seance s : all) {
            if (s.getId() == excludeId) continue;
            if (s.getMoniteur().getId() == m.getId() && s.getDate().equals(date)) {
                LocalTime start = s.getHeure();
                LocalTime end = start.plusHours(1); // 1-hour session
                if (!heure.isBefore(start) && heure.isBefore(end)) {
                    return true;
                }
            }
        }
        return false;
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
                case 4 -> old.setMoniteur(chooseMoniteur(old.getDate(), old.getHeure(), old.getId()));
                case 5 ->old.setCandidat(chooseCandidat(old.getType(), old.getDate(), old.getHeure(), old.getId()));
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
