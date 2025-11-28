package autoEcole;
import java.util.Scanner;

import autoEcole.Controller.CandidatController;
import autoEcole.Controller.MoniteurController;
import autoEcole.Controller.SeanceController;
import autoEcole.Controller.VehiculeController;
import autoEcole.Entities.Candidat;
import autoEcole.Entities.Moniteur;

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

		
		SeanceController s = new SeanceController();
		MoniteurController m =new MoniteurController();
		CandidatController c = new CandidatController();
		VehiculeController v= new VehiculeController();	
		
		///////////
		 boolean loop = true;

	        while (loop) {
	            System.out.println("\n===== Auto Ecole =====");
	            System.out.println("1. Gestion des Seances");
	            System.out.println("2. Gestion des Candidats");
	            System.out.println("3. Gestion des Moniteurs");
	            System.out.println("4. Gestion des Vehicules");
	            System.out.println("5. Planing de la semaine");
	            System.out.println("6. Suivi comptable");
	            System.out.println("0. Exit");
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
		
	}
	private static  void comptable() {
		
		
	}
		

}
