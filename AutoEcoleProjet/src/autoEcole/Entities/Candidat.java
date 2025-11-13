package autoEcole.Entities;

public class Candidat {	
	private String nom;
	private String prenom;
	private String adresse;
	private String telephone;
	private String cin;
	private String typePermis;
	private int nbSeanceCode;
	private int nbSeanceConduite;
	
	public Candidat(String nom, String prenom, String adresse, String telephone, String cin, String typePermis, int nbSeanceCode, int nbSeanceConduite ) {
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.telephone = telephone;
		this.cin = cin;
		this.typePermis = typePermis;
		this.nbSeanceCode = nbSeanceCode;
		this.nbSeanceConduite = nbSeanceConduite;
		
	}
		
}
