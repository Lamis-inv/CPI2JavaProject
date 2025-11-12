package autoEcoleProjet.entities;

public class Candidat extends Person{
	private String cin;
	private String typePermis;
	
	
	public Candidat(String nom, String prenom, String adresse, String telephone, String cin, String typePermis) {
		super(nom, prenom, adresse, telephone);
		this.cin = cin;
		this.typePermis = typePermis;
	}
	
	
	
}
