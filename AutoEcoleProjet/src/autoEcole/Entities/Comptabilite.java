package autoEcole.Entities;

public class Comptabilite {
    private double revenus;   // total income from candidates
    private double depenses;  // total expenses: salaries, maintenance, reparations
    private double benefice;

    public Comptabilite() {
        this.revenus = 0;
        this.depenses = 0;
        this.benefice = 0;
    }

    public double getRevenus() { return revenus; }
    public double getDepenses() { return depenses; }
    public double getBenefice() { return benefice; }

    // methods to increment values
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
