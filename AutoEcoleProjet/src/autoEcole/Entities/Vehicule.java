package autoEcole.Entities;

import java.time.LocalDate;

public class Vehicule {
	
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


	public int getNumImmatricule() {
		return numImmatricule;
	}


	public void setNumImmatricule(int numImmatricule) {
		this.numImmatricule = numImmatricule;
	}


	public LocalDate getDateMiseEnService() {
		return dateMiseEnService;
	}


	public void setDateMiseEnService(LocalDate dateMiseEnService) {
		this.dateMiseEnService = dateMiseEnService;
	}


	public int getKilometrageTotal() {
		return kilometrageTotal;
	}


	public void setKilometrageTotal(int kilometrageTotal) {
		this.kilometrageTotal = kilometrageTotal;
	}


	public int getKmAvantEntretien() {
		return kmAvantEntretien;
	}


	public void setKmAvantEntretien(int kmAvantEntretien) {
		this.kmAvantEntretien = kmAvantEntretien;
	}
	
	
	
	
	
	
	
	
	
	
	
}
