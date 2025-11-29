package autoEcole.Entities;

import java.time.LocalDate;

public class Comptabilite {
    private String type; // "revenu" or "depense"
    private String category; // "candidat", "salaire", "vehicule_maintenance", "vehicule_reparation"
    private double montant;
    private LocalDate date;
    private String details; // extra info, e.g., candidate name, moniteur, vehicle

    public Comptabilite(String type, String category, double montant, LocalDate date, String details) {
        this.type = type;
        this.category = category;
        this.montant = montant;
        this.date = date;
        this.details = details;
    }

    public String getType() { return type; }
    public String getCategory() { return category; }
    public double getMontant() { return montant; }
    public LocalDate getDate() { return date; }
    public String getDetails() { return details; }
}
