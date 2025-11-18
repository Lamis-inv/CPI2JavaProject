package autoEcole.Entities;

public class Candidat {	
	private String nom;
	private String prenom;
	private String adresse;
	private String telephone;
	private int cin;
	private String typePermis;
	private int nbSeanceCode;
	private int nbSeanceConduite;
	
	public Candidat(String nom, String prenom, String adresse, String telephone, int cin, String typePermis, int nbSeanceCode, int nbSeanceConduite ) {
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.telephone = telephone;
		this.cin = cin;
		this.typePermis = typePermis;
		this.nbSeanceCode = nbSeanceCode;
		this.nbSeanceConduite = nbSeanceConduite;
		
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public int getCin() {
		return cin;
	}

	public void setCin(int cin) {
		this.cin = cin;
	}

	public String getTypePermis() {
		return typePermis;
	}

	public void setTypePermis(String typePermis) {
		this.typePermis = typePermis;
	}

	public int getNbSeanceCode() {
		return nbSeanceCode;
	}

	public void setNbSeanceCode(int nbSeanceCode) {
		this.nbSeanceCode = nbSeanceCode;
	}

	public int getNbSeanceConduite() {
		return nbSeanceConduite;
	}

	public void setNbSeanceConduite(int nbSeanceConduite) {
		this.nbSeanceConduite = nbSeanceConduite;
	}
	
		
}
