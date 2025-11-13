package autoEcole.Entities;

public abstract class Seance {
	protected int id;
	protected String date;
	protected Moniteur moniteur;
	protected Candidat candidat;
	
	public Seance(int id, String date, Moniteur moniteur, Candidat candidat) {
		this.id = id;
		this.date = date;
		this.moniteur = moniteur;
		this.candidat = candidat;
	}
	
	
}
