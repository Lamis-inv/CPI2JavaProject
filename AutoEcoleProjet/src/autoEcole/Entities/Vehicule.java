package autoEcole.Entities;

import java.time.LocalDate;

public class Vehicule {
	
    private String numImmatricule;
    private LocalDate dateMiseEnService;
    private int kilometrageTotal;
    private int kmAvantEntretien;
    private String type;

    private LocalDate vignetteExpiration;
    private LocalDate assuranceExpiration;
    private LocalDate visiteTechniqueExpiration;
    private LocalDate vidangeExpiration;

    public Vehicule(String numImmatricule, LocalDate dateMiseEnService, int kilometrageTotal,
                    int kmAvantEntretien, String type,
                    LocalDate vignetteExpiration, LocalDate assuranceExpiration,
                    LocalDate visiteTechniqueExpiration, LocalDate vidangeExpiration) {
        this.numImmatricule = numImmatricule;
        this.dateMiseEnService = dateMiseEnService;
        this.kilometrageTotal = kilometrageTotal;
        this.kmAvantEntretien = kmAvantEntretien;
        this.type = type;
        this.vignetteExpiration = vignetteExpiration;
        this.assuranceExpiration = assuranceExpiration;
        this.visiteTechniqueExpiration = visiteTechniqueExpiration;
        this.vidangeExpiration = vidangeExpiration;
    }

    // Getters and setters
    
    public String getNumImmatricule() {
    	return numImmatricule; 
    	}
    public void setNumImmatricule(String numImmatricule) {
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

    public String getType() {
    	return type; 
    }
    public void setType(String type) {
    	this.type = type; 
    	}

    public LocalDate getVignetteExpiration() {
    	return vignetteExpiration; 
    	}
    public void setVignetteExpiration(LocalDate vignetteExpiration) {
    	this.vignetteExpiration = vignetteExpiration; 
    	}

    public LocalDate getAssuranceExpiration() {
    	return assuranceExpiration; 
    	}
    public void setAssuranceExpiration(LocalDate assuranceExpiration) {
    	this.assuranceExpiration = assuranceExpiration; 
    	}

    public LocalDate getVisiteTechniqueExpiration() {
    	return visiteTechniqueExpiration; 
    	}
    public void setVisiteTechniqueExpiration(LocalDate visiteTechniqueExpiration) {
    	this.visiteTechniqueExpiration = visiteTechniqueExpiration; 
    	}

    public LocalDate getVidangeExpiration() {
    	return vidangeExpiration; 
    	}
    public void setVidangeExpiration(LocalDate vidangeExpiration) {
    	this.vidangeExpiration = vidangeExpiration; 
    	}

	@Override
	public String toString() {
		return "Vehicule [numImmatricule=" + numImmatricule + ", dateMiseEnService=" + dateMiseEnService
				+ ", kilometrageTotal=" + kilometrageTotal + ", kmAvantEntretien=" + kmAvantEntretien + ", type=" + type
				+ ", vignetteExpiration=" + vignetteExpiration + ", assuranceExpiration=" + assuranceExpiration
				+ ", visiteTechniqueExpiration=" + visiteTechniqueExpiration + ", vidangeExpiration="
				+ vidangeExpiration + "]";
	}
    
    
}
