package autoEcole.Entities;

import java.time.LocalDate;

public class Comptabilite {
    private String type;        // "revenu" or "depense"
    private String categorie;   // e.g., "candidat", "salaire", "reparation"
    private double montant;
    private LocalDate date;

    // totals (optional)
    private double revenus;
    private double depenses;
    private double benefice;

    // No-args constructor
    public Comptabilite() {
        this.revenus = 0;
        this.depenses = 0;
        this.benefice = 0;
    }

    // ✅ Constructor with all fields
    public Comptabilite(String type, String categorie, double montant, LocalDate date) {
        this.type = type;
        this.categorie = categorie;
        this.montant = montant;
        this.date = date;
    }

    // Getters and setters
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getCategorie() { return categorie; }
    public void setCategorie(String categorie) { this.categorie = categorie; }

    public double getMontant() { return montant; }
    public void setMontant(double montant) { this.montant = montant; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public double getRevenus() { return revenus; }
    public double getDepenses() { return depenses; }
    public double getBenefice() { return benefice; }

    // methods to increment totals
    public void ajouterRevenu(double montant) {
        revenus += montant;
        calculerBenefice();
    }

    public void ajouterDepense(double montant) {
        depenses += montant;
        calculerBenefice();
    }

    private void calculerBenefice() {
        benefice = revenus - depenses;
    }
}
