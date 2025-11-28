package autoEcole.Entities;

import java.time.LocalDate;

public class Vehicule {
    
    private String numImmatricule;
    private LocalDate dateMiseEnService;
    private int kilometrageTotal;
    private int kmAvantEntretien;
    private String type;

    // Dernières dates effectuées
    private LocalDate vignetteDerniereDate;
    private LocalDate assuranceDerniereDate;
    private LocalDate visiteTechniqueDerniereDate;
    private LocalDate vidangeDerniereDate;

    public Vehicule(String numImmatricule, LocalDate dateMiseEnService, int kilometrageTotal,
                    int kmAvantEntretien, String type,
                    LocalDate vignetteDerniereDate, LocalDate assuranceDerniereDate,
                    LocalDate visiteTechniqueDerniereDate, LocalDate vidangeDerniereDate) {

        this.numImmatricule = numImmatricule;
        this.dateMiseEnService = dateMiseEnService;
        this.kilometrageTotal = kilometrageTotal;
        this.kmAvantEntretien = kmAvantEntretien;
        this.type = type;

        this.vignetteDerniereDate = vignetteDerniereDate;
        this.assuranceDerniereDate = assuranceDerniereDate;
        this.visiteTechniqueDerniereDate = visiteTechniqueDerniereDate;
        this.vidangeDerniereDate = vidangeDerniereDate;
    }

    // Getters & Setters

    public String getNumImmatricule() { return numImmatricule; }
    public void setNumImmatricule(String numImmatricule) { this.numImmatricule = numImmatricule; }

    public LocalDate getDateMiseEnService() { return dateMiseEnService; }
    public void setDateMiseEnService(LocalDate dateMiseEnService) { this.dateMiseEnService = dateMiseEnService; }

    public int getKilometrageTotal() { return kilometrageTotal; }
    public void setKilometrageTotal(int kilometrageTotal) { this.kilometrageTotal = kilometrageTotal; }

    public int getKmAvantEntretien() { return kmAvantEntretien; }
    public void setKmAvantEntretien(int kmAvantEntretien) { this.kmAvantEntretien = kmAvantEntretien; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public LocalDate getVignetteDerniereDate() { return vignetteDerniereDate; }
    public void setVignetteDerniereDate(LocalDate vignetteDerniereDate) { this.vignetteDerniereDate = vignetteDerniereDate; }

    public LocalDate getAssuranceDerniereDate() { return assuranceDerniereDate; }
    public void setAssuranceDerniereDate(LocalDate assuranceDerniereDate) { this.assuranceDerniereDate = assuranceDerniereDate; }

    public LocalDate getVisiteTechniqueDerniereDate() { return visiteTechniqueDerniereDate; }
    public void setVisiteTechniqueDerniereDate(LocalDate visiteTechniqueDerniereDate) { this.visiteTechniqueDerniereDate = visiteTechniqueDerniereDate; }

    public LocalDate getVidangeDerniereDate() { return vidangeDerniereDate; }
    public void setVidangeDerniereDate(LocalDate vidangeDerniereDate) { this.vidangeDerniereDate = vidangeDerniereDate; }

    @Override
    public String toString() {
        return "Vehicule [" +
                "Immatricule=" + numImmatricule +
                ", Date Mise en Service=" + dateMiseEnService +
                ", Kilométrage Total=" + kilometrageTotal +
                ", Km Avant Entretien=" + kmAvantEntretien +
                ", Type=" + type +
                ", Vignette Dernière Date=" + vignetteDerniereDate +
                ", Assurance Dernière Date=" + assuranceDerniereDate +
                ", Visite Technique Dernière Date=" + visiteTechniqueDerniereDate +
                ", Vidange Dernière Date=" + vidangeDerniereDate +
                "]";
    }
}
