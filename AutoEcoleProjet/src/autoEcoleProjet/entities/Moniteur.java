package autoEcoleProjet.entities;

public class Moniteur extends Person{
	private int id;
	private boolean isDisponible;
	private int nbHeures;
	
	public Moniteur(String nom, String prenom, String adresse, String telephone, int id, boolean isDisponible,
			int nbHeures) {
		super(nom, prenom, adresse, telephone);
		this.id = id;
		this.isDisponible = isDisponible;
		this.nbHeures = nbHeures;
	}
	
	
	
}
