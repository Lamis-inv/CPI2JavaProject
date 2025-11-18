package autoEcole.Entities;

import java.time.LocalDate;

public abstract class Vehicule {
	
	protected int numImmatricule;
	protected LocalDate dateMiseEnService;
	protected int kilometrageTotal;
	protected int kmAvantEntretien;
	
	
	public Vehicule(int numImmatricule, LocalDate dateMiseEnService, int kilometrageTotal, int kmAvantEntretien) {
		this.numImmatricule = numImmatricule;
		this.dateMiseEnService = dateMiseEnService;
		this.kilometrageTotal = kilometrageTotal;
		this.kmAvantEntretien = kmAvantEntretien;
	}
	
	
	
	
	
	
	
	
	
}
