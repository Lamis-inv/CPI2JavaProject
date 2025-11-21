package autoEcole.Entities;

import java.time.LocalDate;
import java.time.LocalTime;

public class Seance {
	private int id;
	private String type;
	private LocalDate  date;
	private LocalTime  heure;
	private Moniteur moniteur;
	private Candidat candidat;
	private double prix;
	private Vehicule vehicule;
	
	public Seance(int id,String type, LocalDate date, LocalTime heure, Moniteur moniteur, Candidat candidat, double prix,
			Vehicule vehicule) {
		this.id = id;
		this.type=type;
		this.date = date;
		this.heure = heure;
		this.moniteur = moniteur;
		this.candidat = candidat;
		this.prix = prix;
		this.vehicule = vehicule;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getHeure() {
		return heure;
	}

	public void setHeure(LocalTime heure) {
		this.heure = heure;
	}

	public Moniteur getMoniteur() {
		return moniteur;
	}

	public void setMoniteur(Moniteur moniteur) {
		this.moniteur = moniteur;
	}

	public Candidat getCandidat() {
		return candidat;
	}

	public void setCandidat(Candidat candidat) {
		this.candidat = candidat;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public Vehicule getVehicule() {
		return vehicule;
	}

	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}
	
	
	
	
}
