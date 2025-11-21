package autoEcole.UI;

import java.util.Scanner;

import autoEcole.Controller.MoniteurController;
import autoEcole.Entities.Candidat;
import autoEcole.Entities.Moniteur;

public class MoniteurUI {

	MoniteurController controller =new MoniteurController();
	
	public void init() {
		boolean test=true;
		
		do {
			System.out.println("===== Moniteur Menu =====");
            System.out.println("1. Add Moniteur");
            System.out.println("2. Show All Moniteurs");
            System.out.println("3. Find By ID");
            System.out.println("4. Update Moniteur");
            System.out.println("5. Delete Moniteur");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
    		Scanner scanner = new Scanner(System.in);
    		int choix = scanner.nextInt();
			switch(choix) {
			case 1:
			{
					saisir();
					break;
			}
			case 2: {
				controller.findAll();
				break;
			}
			case 3: {
				findById();
				break;
			}
			case 4: {
				updateMoniteur();
				break;
			}
			case 5: {
				deleteMoniteur();
				break;
			}
			case 0:{
				test=false;
				break;
			}
			default:
				System.out.println("aucun choix a ete choisie");
			
			}
			
		}while(test);
	}
	
	public void saisir() {
	    Scanner scanner = new Scanner(System.in);

	    System.out.print("Enter Nom: ");
	    String nom = scanner.nextLine();

	    int id;
	    while (true) {
	        System.out.print("Enter ID (integer): ");
	        if (scanner.hasNextInt()) {
	            id = scanner.nextInt();
	            scanner.nextLine(); // consume newline
	            break;
	        } else {
	            System.out.println("Invalid input! Please enter an integer for ID.");
	            scanner.nextLine();
	        }
	    }

	    boolean dispo;
	    while (true) {
	        System.out.print("Is Disponible (true/false): ");
	        if (scanner.hasNextBoolean()) {
	            dispo = scanner.nextBoolean();
	            scanner.nextLine(); // consume newline
	            break;
	        } else {
	            System.out.println("Invalid input! Please enter true or false.");
	            scanner.nextLine();
	        }
	    }

	    Moniteur m = new Moniteur(id, nom, dispo,0);
	    controller.add(m); // le controller appellera la repo
	}


	private void deleteMoniteur() {
		Scanner scanner = new Scanner(System.in);
        System.out.print("Enter ID to delete: ");
        int id = scanner.nextInt();
        controller.delete(id);
    }
	private void findById() {
		Scanner scanner = new Scanner(System.in);
        System.out.print("Enter ID : ");
        int id = scanner.nextInt();
        controller.findByCin(id);
    }
    private void updateMoniteur() {
    	Scanner scanner = new Scanner(System.in);
        System.out.print("Enter ID of Moniteur to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Moniteur old = controller.getById(id);
        if (old == null) {
            System.out.println("Moniteur not found!");
            return;
        }

        boolean updating = true;
        while (updating) {
            System.out.println("\n===== Update Moniteur =====");
            System.out.println("1. Update Name");
            System.out.println("2. Update ID");
            System.out.println("3. Update Disponibilite");
            System.out.println("0. Save and Exit");
            System.out.print("Choose: ");

            int choice = scanner.hasNextInt() ? scanner.nextInt() : -1;
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter new Name: ");
                    old.setNom(scanner.nextLine());
                }
                case 2 -> {
                    System.out.print("Enter new ID: ");
                    old.setId(scanner.nextInt());
                    scanner.nextLine();
                }
                case 3 -> {
                    System.out.print("Enter Disponibilite (true/false): ");
                    old.setDisponible(scanner.nextBoolean());
                    scanner.nextLine();
                }
                case 0 -> {
                    controller.update(id, old);
                    System.out.println("Changes saved successfully!");
                    updating = false;
                }
                default -> System.out.println("Invalid choice!");
            }
        }

    }
}
