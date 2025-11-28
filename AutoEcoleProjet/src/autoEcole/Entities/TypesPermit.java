package autoEcole.Entities;

public enum TypesPermit {
    A1("A1", 15, 28),
    A("A", 15, 28),
    B("B", 15, 28),
    BE("B+E", 15, 30),
    C("C", 15, 30),
    CE("C+E", 15, 30),
    D("D", 15, 30),
    DE("D+E", 15, 30),
    D1("D1", 15, 28),
    H("H", 15, 25);

    private final String label;
    private final int prixCode;       // prix par séance
    private final int prixConduite;   // prix par heure

    TypesPermit(String label, int prixCode, int prixConduite) {
        this.label = label;
        this.prixCode = prixCode;
        this.prixConduite = prixConduite;
    }

    public String getLabel() { return label; }
    public int getPrixCode() { return prixCode; }
    public int getPrixConduite() { return prixConduite; }
    @Override
    public String toString() {
        return label;
    }

}
