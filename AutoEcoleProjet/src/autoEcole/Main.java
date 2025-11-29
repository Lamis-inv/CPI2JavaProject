package autoEcole;
import java.util.Scanner;

import autoEcole.Controller.CandidatController;
import autoEcole.Controller.MoniteurController;
import autoEcole.Controller.SeanceController;
import autoEcole.Controller.VehiculeController;
import autoEcole.Entities.Candidat;
import autoEcole.Entities.Moniteur;
import autoEcole.Entities.Seance;
import autoEcole.Service.CandidatService;
import autoEcole.UI.CandidatUI;

public class Main {
	
	private static  Scanner scanner = new Scanner(System.in);
	private static int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid number. Try again: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
	public static void main(String[] args) {
<<<<<<< HEAD
		 
	}	
=======

		
		SeanceController s = new SeanceController();
		MoniteurController m =new MoniteurController();
		CandidatController c = new CandidatController();
		VehiculeController v= new VehiculeController();	
		
		///////////
		 boolean loop = true;

	        while (loop) {
	            System.out.println("\n============= Auto Ecole =============\n\n");
	            System.out.println("1. Gestion des Seances\n");
	            System.out.println("2. Gestion des Candidats\n");
	            System.out.println("3. Gestion des Moniteurs\n");
	            System.out.println("4. Gestion des Vehicules\n");
	            System.out.println("5. Planing de la semaine\n");
	            System.out.println("6. Suivi comptable\n");
	            System.out.println("0. Exit\n");
	            System.out.print("Choose: ");

	            int choice = getIntInput();

	            switch (choice) {
	                case 1 -> s.init();
	                case 2 -> c.init();
	                case 3 -> m.init();
	                //case 4 -> v.init();
	                case 5 -> planing();
	                case 6 -> comptable();
	                case 0 -> loop = false;
	                default -> System.out.println("Invalid choice!");
	            }
	        }
	        
	       
		
	}
	private static void planing() {
	    Scanner scanner = new Scanner(System.in);
	    SeanceController seanceController = new SeanceController();
	    Seance[] allSeances = seanceController.getAll();

	    // Get current month/year
	    java.time.LocalDate today = java.time.LocalDate.now();
	    java.time.YearMonth ym = java.time.YearMonth.of(today.getYear(), today.getMonth());
	    int daysInMonth = ym.lengthOfMonth();

	    // Hours
	    int startHour = 8;
	    int endHour = 18;

	    int cellWidth = 8; // width of each day in calendar

	    // Build calendar header
	    System.out.println("\n=============== Calendrier du Mois ================\n");
	    System.out.println("Lun    Mar    Mer    Jeu    Ven    Sam    Dim");

	    // Build the first week offset
	    java.time.LocalDate firstDay = ym.atDay(1);
	    int dayOfWeekOffset = firstDay.getDayOfWeek().getValue(); // MONDAY=1 ... SUNDAY=7
	    int printed = 0;

	    // Print empty cells for the offset
	    for (int i = 1; i < dayOfWeekOffset; i++) {
	        System.out.print("       "); // 7 spaces for empty day
	        printed++;
	    }

	    // Print the days with '*' if there's a séance
	    for (int day = 1; day <= daysInMonth; day++) {
	        java.time.LocalDate currentDate = ym.atDay(day);

	        boolean hasSeance = false;
	        for (Seance s : allSeances) {
	            if (s != null && s.getDate() != null && s.getDate().isEqual(currentDate)) {
	                hasSeance = true;
	                break;
	            }
	        }

	        String dayStr = String.format("%2d", day) + (hasSeance ? "*" : " ");
	        System.out.printf("%-7s", dayStr);
	        printed++;

	        if (printed % 7 == 0) System.out.println(); // new line at week end
	    }
	    System.out.println("\n");

	    // Ask user to select a day to see hourly planning
	    while (true) {
	        System.out.print("Tapez le jour pour voir le planning (0 pour revenir): ");
	        int choix = -1;
	        while (!scanner.hasNextInt()) {
	            System.out.print("Veuillez entrer un nombre valide: ");
	            scanner.next();
	        }
	        choix = scanner.nextInt();
	        scanner.nextLine(); // consume newline

	        if (choix == 0) return;
	        if (choix < 1 || choix > daysInMonth) {
	            System.out.println("Jour invalide !");
	            continue;
	        }

	        java.time.LocalDate selectedDate = ym.atDay(choix);

	        System.out.println("\nPlanning pour le " + selectedDate + "\n");
	        System.out.println("Heure  | Seance(s)");
	        System.out.println("-------------------------");

	        for (int h = startHour; h <= endHour; h++) {
	            int hour = h;
	            System.out.printf("%02d:00   | ", hour);

	            // Get seances at this hour
	            boolean empty = true;
	            for (Seance s : allSeances) {
	                if (s != null && s.getDate() != null && s.getHeure() != null) {
	                    if (s.getDate().isEqual(selectedDate) && s.getHeure().getHour() == hour) {
	                        empty = false;
	                        String typeAbbr = (s.getType() == null) ? "" : (s.getType().length() > 3 ? s.getType().substring(0, 3) : s.getType());
	                        String cand = s.getCandidat() != null ? (s.getCandidat().getNom() + " " + s.getCandidat().getPrenom()) : "Candidat";
	                        String mon = s.getMoniteur() != null ? s.getMoniteur().getNom() : "Moniteur";
	                        System.out.printf("%s/%s | ", typeAbbr, cand + "/" + mon);
	                    }
	                }
	            }

	            if (empty) System.out.print("-");

	            System.out.println();
	        }

	        System.out.println("\nTapez ENTER pour revenir au calendrier...");
	        scanner.nextLine();
	    }
	}


	private static  void comptable() {
		
		
	}
		
>>>>>>> branch 'master' of https://github.com/Lamis-inv/CPI2JavaProject.git

}
