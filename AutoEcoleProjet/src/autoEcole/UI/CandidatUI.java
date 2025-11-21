package autoEcole.UI;

import java.util.Scanner;

import autoEcole.Controller.CandidatController;
import autoEcole.Entities.Candidat;
import autoEcole.Entities.Seance;
import autoEcole.Entities.TypesPermit;

public class CandidatUI {
	CandidatController controller = new CandidatController();
	
	
	public void init() {
		boolean test=true;
		
		do {
			System.out.println("===== Candidate Menu =====");
            System.out.println("1. Add Candidate");
            System.out.println("2. Show All Candidates");
            System.out.println("3. Find By Cin");
            System.out.println("4. Update Candidate");
            System.out.println("5. Delete Candidate");
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
				findByCin();
				break;
			}
			case 4: {
				updateCandidate();
				break;
			}
			case 5: {
				deleteCandidate();
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
		System.out.println("saisir votre nom:");
		String nom = scanner.nextLine();
		System.out.println("saisir votre prenom:");
		String pre = scanner.nextLine();
		System.out.println("saisir votre adresse");
		String adr = scanner.nextLine();
		System.out.println("saisir votre Tel");
		String tel = scanner.nextLine();
		
		System.out.println("saisir votre CIN");
		String cin = scanner.nextLine();
		while(cin.length()!=8) {
			System.out.println("saisir votre CIN");
			cin = scanner.next();
		}
		int c = Integer.parseInt(cin);
		TypesPermit permit =typepermit();
		Seance[] seances = new Seance[0]; // no sessions yet
		double paidAmount = 0; // initial paid amount
		Candidat newCandidate = new Candidat(nom, pre, adr, tel, c, permit, 0, 0, 0, paidAmount, seances);
		controller.add(newCandidate);

		
	}


	private void deleteCandidate() {
		Scanner scanner = new Scanner(System.in);
        System.out.print("Enter CIN to delete: ");
        int cin = scanner.nextInt();
        controller.delete(cin);
    }
	private void findByCin() {
		Scanner scanner = new Scanner(System.in);
        System.out.print("Enter CIN : ");
        int cin = scanner.nextInt();
        controller.findByCin(cin);
    }
	private void updateCandidate() {
	    Scanner scanner = new Scanner(System.in);
	    System.out.print("Enter CIN of candidate to update: ");
	    int cin = scanner.nextInt();
	    scanner.nextLine();

	    // Get existing candidate
	    Candidat old = controller.getByCin(cin);
	    if (old == null) {
	        System.out.println("Candidate not found!");
	        return;
	    }

	    int choice;
	    do {
	        System.out.println("\n===== Update Candidate =====");
	        System.out.println("1. Update Name");
	        System.out.println("2. Update Prenom");
	        System.out.println("3. Update Adresse");
	        System.out.println("4. Update Téléphone");
	        System.out.println("5. Update Type Permis");
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
	                System.out.print("Enter new prenom: ");
	                old.setPrenom(scanner.nextLine());
	                break;

	            case 3:
	                System.out.print("Enter new address: ");
	                old.setAdresse(scanner.nextLine());
	                break;

	            case 4:
	                System.out.print("Enter new telephone: ");
	                old.setTelephone(scanner.nextLine());
	                break;

	            case 5:
	                TypesPermit p=typepermit();
	                old.setTypePermis(p);
	                break;

	            case 0:
	                controller.update(cin, old);
	                System.out.println("Changes saved successfully!");
	                break;

	            default:
	                System.out.println("Invalid choice!");
	        }

	    } while (choice != 0);
	}
	
	public TypesPermit typepermit() {

	    Scanner scanner = new Scanner(System.in);
	    TypesPermit result = null;

	    boolean repeat = true;

	    while (repeat) {

	        System.out.println("===== Choisir un Type de Permis =====");

	        TypesPermit[] all = TypesPermit.values();

	        for (int i = 0; i < all.length; i++) {
	            System.out.println((i + 1) + ") " + all[i]);
	        }

	        System.out.print("Choix : ");

	        // FIX SCANNER
	        while (!scanner.hasNextInt()) {
	            System.out.println("Veuillez entrer un nombre !");
	            scanner.next();
	        }

	        int choix = scanner.nextInt();
	        scanner.nextLine(); // consume \n

	        if (choix >= 1 && choix <= all.length) {
	            result = all[choix - 1];
	            repeat = false;
	        } else {
	            System.out.println("Choix invalide. Réessayez.");
	        }
	    }

	    return result;
	}


	
	
}
