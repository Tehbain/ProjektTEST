package ordination;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract class Ordination {
    private LocalDate startDen;
    private LocalDate slutDen;
    private final Lægemiddel lægemiddel;

    public Ordination(LocalDate startDen, LocalDate slutDen, Lægemiddel lægemiddel) {
        this.startDen = startDen;
        this.slutDen = slutDen;
        this.lægemiddel = lægemiddel;
    }

    public LocalDate getStartDen() {
        return startDen;
    }

    public LocalDate getSlutDen() {
        return slutDen;
    }

    /** DONE: Returner antal hele dage mellem startdato og slutdato. Begge dage inklusive. */
    public int antalDage() {
        return (int) ChronoUnit.DAYS.between(startDen, slutDen) + 1;
    }

    @Override
    public String toString() {
        return startDen.toString();
    }

    /** DONE: Returner den totale dosis, der er givet i den periode ordinationen er gyldig. */
    public abstract double samletDosis();

    /** DONE Returner den gennemsnitlige dosis givet pr dag i den periode, ordinationen er gyldig. */
    public abstract double doegnDosis();

    /** Returner ordinationstypen som en String. */
    public abstract String getType();
}
