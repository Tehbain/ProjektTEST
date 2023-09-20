package ordination;

import java.time.LocalDate;

public class PN extends Ordination {
    private double antalEnheder;

    /**
     * Registrer, at der er givet en dosis paa dagen givesDen.
     * Returner true, hvis givesDen er inden for ordinationens gyldighedsperiode, og datoen huskes.
     * Returner false ellers, og datoen givesDen ignoreres.
     */
    public boolean givDosis(LocalDate givesDen) {

        return false;
    }

    /** Returner antal gange ordinationen er anvendt. */
    public int getAntalGangeGivet() {

        return -1;
    }

    @Override
    public double samletDosis() {
        return 0;
    }

    @Override
    public double doegnDosis() {
        return 0;
    }

    @Override
    public String getType() {
        return null;
    }
}
