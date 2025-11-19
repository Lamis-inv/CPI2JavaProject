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
		System.out.println("saisir votre Nom:");
		String nom = scanner.nextLine();
		System.out.println("saisir votre ID:");
		int id = scanner.nextInt();
		System.out.println("saisir votre Disponibilite");
		boolean dis = scanner.hasNextBoolean();
		
		System.out.println("saisir votre Tel");
		String tel = scanner.nextLine();
		
		
		controller.add(new Moniteur(id, nom, dis, 0));
		
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
	    scanner.nextLine();

	    // Get existing candidate
	    Moniteur old = controller.getById(id);
	    if (old == null) {
	        System.out.println("Moniteur not found!");
	        return;
	    }

	    int choice;
	    do {
	        System.out.println("\n===== Update Candidate =====");
	        System.out.println("1. Update Name");
	        System.out.println("2. Update ID");
	        System.out.println("3. Update Disponibilite");
	        System.out.println("4. Update Téléphone");

	        System.out.println("0. Save and Exit");
	        System.out.print("Choose: ");

	        choice = scanner.nextInt();
	        scanner.nextLine(); // consume newline

	        switch (choice) {
	            case 1:
	                System.out.print("Enter new name: ");
	                old.setNom(scanner.nextLine());
	                break;

	            case 2:
	                System.out.print("Enter new ID: ");
	                old.setId(scanner.nextInt());
	                break;

	            case 3:
	                System.out.print("Enter Disponibilite: ");
	                old.setDisponible(scanner.hasNextBoolean());
	                break;

	            case 4:
	                System.out.print("Enter new telephone: ");
	                //old.setTelephone(scanner.nextLine());
	                break;
	            case 0:
	                controller.update(id, old);
	                System.out.println("Changes saved successfully!");
	                break;

	            default:
	                System.out.println("Invalid choice!");
	        }

	    } while (choice != 0);
	}
}
