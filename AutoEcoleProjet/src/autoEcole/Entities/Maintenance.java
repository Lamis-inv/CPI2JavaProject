package autoEcole.Entities;

import java.time.LocalDate;

public class Maintenance {
    private String immatriculation;
    private String description;
    private LocalDate date;
    private double cout;

    public Maintenance(String immatriculation, String description, LocalDate date, double cout) {
        this.immatriculation = immatriculation;
        this.description = description;
        this.date = date;
        this.cout = cout;
    }

    // Getters and setters
    public String getImmatriculation() { return immatriculation; }
    public void setImmatriculation(String immatriculation) { this.immatriculation = immatriculation; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public double getCout() { return cout; }
    public void setCout(double cout) { this.cout = cout; }
}
