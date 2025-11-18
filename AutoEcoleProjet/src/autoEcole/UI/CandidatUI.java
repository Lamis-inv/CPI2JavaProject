package autoEcole.UI;

import java.util.Scanner;

import autoEcole.Controller.CandidatController;
import autoEcole.Entities.Candidat;

public class CandidatUI {
	CandidatController controller = new CandidatController();
	
	
	public void init() {
		boolean test=true;
		
		do {
			System.out.println("===== Candidate Menu =====");
            System.out.println("1. Add Candidate");
            System.out.println("2. Show All Candidates");
            System.out.println("3. Update Candidate");
            System.out.println("4. Delete Candidate");
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
				//updateCandidat();
				break;
			}
			case 4: {
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
		System.out.println("saisir votre Type permit");
		String permit = scanner.nextLine();
		controller.add(new Candidat(nom, pre, adr, tel, cin, permit, 0, 0));
		
	}
	private void dupdateCandidate() {
		Scanner scanner = new Scanner(System.in);
        System.out.print("Enter CIN to updates: ");
        String cin = scanner.nextLine();
        controller.delete(cin);
    }
	private void deleteCandidate() {
		Scanner scanner = new Scanner(System.in);
        System.out.print("Enter CIN to delete: ");
        String cin = scanner.nextLine();
        controller.delete(cin);
    }
	
	
}
