package autoEcole.Entities;

public class SeanceCode extends Seance{
	private int prix;

	public SeanceCode(int id, String date, Moniteur moniteur, Candidat candidat, int prix) {
		super(id, date, moniteur, candidat);
		this.prix = prix;
	}
	
	
}
