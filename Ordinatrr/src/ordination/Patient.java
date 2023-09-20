package ordination;

import java.util.ArrayList;
import java.util.List;

public class Patient {
    private final String cprnr;
    private final String navn;
    private final double vægt;
    /** Ass. 0..* til Patient */
    private ArrayList<Ordination> ordinationer = new ArrayList<>();

    public Patient(String cprnr, String navn, double vaegt) {
        this.cprnr = cprnr;
        this.navn = navn;
        this.vægt = vaegt;
    }

    public String getCprnr() {
        return cprnr;
    }

    public String getNavn() {
        return navn;
    }

    public double getVægt() {
        return vægt;
    }

    public void addOrdination (Ordination ordination) {
        ordinationer.add(ordination);
    }
    public void addOrdination (List<Ordination> ordinationer) {
        this.ordinationer.addAll(ordinationer);
    }

    public ArrayList<Ordination> getOrdinationer() {
        return new ArrayList<>(ordinationer);
    }

    @Override
    public String toString() {
        return navn + "  " + cprnr;
    }
}
