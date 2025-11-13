package autoEcole.UI;

import java.util.Scanner;

import autoEcole.Entities.Candidat;

public class CandidatUI {
	
	
	public static Candidat saisir() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("saisir votre nom:");
		String nom = scanner.next();
		System.out.println("saisir votre prenom:");
		String pre = scanner.next();
		System.out.println("saisir votre adresse");
		String adr = scanner.next();
		System.out.println("saisir votre Tel");
		String tel = scanner.next();
		System.out.println("saisir votre CIN");
		String cin = scanner.next();
		System.out.println("saisir votre Type permit");
		String permit = scanner.next();
		scanner.close();
		return new Candidat(nom, pre, adr, tel, cin, permit, 0, 0);
		
	}
}
