package autoEcole.Entities;

public class Moniteur {
    private int id;
    private String nom;
    private boolean isDisponible;
    private int nbHeuresTravaillees;
    private double salaireBase;

    public Moniteur(int id, String nom, boolean isDisponible, int nbHeuresTravaillees, double salaireBase) {
        this.id = id;
        this.nom = nom;
        this.isDisponible = isDisponible;
        this.nbHeuresTravaillees = nbHeuresTravaillees;
        this.salaireBase = salaireBase;
    }

    public Moniteur(int id, String nom, boolean isDisponible, int nbHeuresTravaillees) {
        this.id = id;
        this.nom = nom;
        this.isDisponible = true;
        this.nbHeuresTravaillees = 0;
        this.salaireBase = 600; // default salary
    }
    public Moniteur(int id, String nom) {
        this.id = id;
        this.nom = nom;
        this.isDisponible = true;
        this.nbHeuresTravaillees = 0;
        this.salaireBase = 600; // default salary
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public boolean isDisponible() {
        return isDisponible;
    }
    public void setDisponible(boolean isDisponible) {
        this.isDisponible = isDisponible;
    }
    public int getNbHeuresTravaillees() {
        return nbHeuresTravaillees;
    }
    public void setNbHeuresTravaillees(int nbHeuresTravaillees) {
        this.nbHeuresTravaillees = nbHeuresTravaillees;
    }
    public double getSalaireBase() {
        return salaireBase;
    }
    public void setSalaireBase(double salaireBase) {
        this.salaireBase = salaireBase;
    }
}
