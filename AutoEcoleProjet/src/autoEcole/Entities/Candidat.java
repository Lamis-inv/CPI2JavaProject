package autoEcole.Entities;

public class Candidat {	
	private String nom;
	private String prenom;
	private String adresse;
	private String telephone;
	private int cin;
	private TypesPermit typePermis;
	private int nbSeanceCode;
	private int nbSeanceConduite;
	private double totalPrice;
    private double paidAmount;
    private Seance[] seances;
    private boolean codeExamPassed;

    
    public boolean isCodeExamPassed() {
        return codeExamPassed;
    }
    public boolean getCodeExamPassed() {
        return codeExamPassed;
    }

    public void setCodeExamPassed(boolean codeExamPassed) {
        this.codeExamPassed = codeExamPassed;
    }

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public int getCin() {
		return cin;
	}
	public void setCin(int cin) {
		this.cin = cin;
	}
	
	public TypesPermit getTypePermis() {
		return typePermis;
	}
	public void setTypePermis(TypesPermit typePermis) {
		this.typePermis = typePermis;
	}
	public Candidat(String nom, String prenom, String adresse, String telephone, int cin, TypesPermit typePermis,
	        int nbSeanceCode, int nbSeanceConduite, double totalPrice, double paidAmount, Seance[] seances) {
	    this.nom = nom;
	    this.prenom = prenom;
	    this.adresse = adresse;
	    this.telephone = telephone;
	    this.cin = cin;
	    this.typePermis = typePermis;
	    this.nbSeanceCode = nbSeanceCode;
	    this.nbSeanceConduite = nbSeanceConduite;
	    this.totalPrice = totalPrice;
	    this.paidAmount = paidAmount;
	    this.seances = seances;
	    this.codeExamPassed = false; // add here
	}

	public Candidat(String nom, String prenom, String adresse, String telephone, int cin, TypesPermit typePermis,
			 double totalPrice, double paidAmount, Seance[] seances) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.telephone = telephone;
		this.cin = cin;
		this.typePermis = typePermis;
		this.nbSeanceCode = 0;
		this.nbSeanceConduite = 0;
		this.totalPrice = totalPrice;
		this.paidAmount = paidAmount;
		this.seances = seances;
		this.codeExamPassed = false;
	}
	public void addCodeSession() {
	    nbSeanceCode++;
	    totalPrice += typePermis.getPrixCode();
	}

	public void addConduiteSession() {
	    nbSeanceConduite++;
	    totalPrice += typePermis.getPrixConduite();
	}

	public void pay(double amount) {
	    if(amount <= 0) return;
	    paidAmount += amount; // increments, never decrements counts
	}

	public double getRemainingAmount() {
	    return totalPrice - paidAmount;
	}

	public int getNbSeanceCode() {
		return nbSeanceCode;
	}
	public void setNbSeanceCode(int nbSeanceCode) {
		this.nbSeanceCode = nbSeanceCode;
	}
	public int getNbSeanceConduite() {
		return nbSeanceConduite;
	}
	public void setNbSeanceConduite(int nbSeanceConduite) {
		this.nbSeanceConduite = nbSeanceConduite;
	}
	public double getTotalPrice() {
	    return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public double getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}
	public Seance[] getSeances() {
		return seances;
	}
	public void setSeances(Seance[] seances) {
		this.seances = seances;
	}
	
	
 
		
}
