package autoEcole.Entities;

public class Vehicule {

    private String numImmatricule;
    private String dateMiseEnService;
    private int kilometrageTotal;
    private int kmAvantEntretien;
    private String type;

    public Vehicule(String numImmatricule, String dateMiseEnService,int kilometrageTotal, int kmAvantEntretien, String type) {
        this.numImmatricule = numImmatricule;
        this.dateMiseEnService = dateMiseEnService;
        this.kilometrageTotal = kilometrageTotal;
        this.kmAvantEntretien = kmAvantEntretien;
        this.type = type;
    }

    public String getType() {
    	return type; 
    	}
    public void setType(String type) {
    	this.type = type; 
    	}

    public String getNumImmatricule() {
    	return numImmatricule; 
    	}
    public void setNumImmatricule(String numImmatricule) {
    	this.numImmatricule = numImmatricule; 
    	}

    public String getDateMiseEnService() {
    	return dateMiseEnService; 
    	}
    public void setDateMiseEnService(String dateMiseEnService) {
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
