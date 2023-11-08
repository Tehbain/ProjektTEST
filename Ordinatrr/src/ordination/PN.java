package ordination;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/*
    Ved PN forstås, at medicinen gives efter behov, i den periode ordinationen er gyldig. Her registrerer man,
    hvor mange enheder patienten skal have af medicinen, når der er behov. På en PN-ordination holdes der rede
    på, hvilke dage patienten har fået medicinen. Bemærk, at patienten godt kan få medicinen flere gange på
    samme dag, dvs. den samme dato kan registreres flere gange.
 */
public class PN extends Ordination {
    private double antalEnheder;
    private final ArrayList<LocalDate> givetDato = new ArrayList<>(); //For PN-ordinationer skal man gemme alle tidspunkter (datoer) for, hvornår ordinationen er givet.
    private int antalGangeGivet;

    public PN(LocalDate startDen, LocalDate slutDen, Lægemiddel lægemiddel) {
        super(startDen, slutDen, lægemiddel);
    }

    /**
     * Registrer, at der er givet en dosis paa dagen givesDen.
     * Returner true, hvis givesDen er inden for ordinationens gyldighedsperiode, og datoen huskes.
     * Returner false ellers, og datoen givesDen ignoreres.
     */
    public boolean givDosis(LocalDate givesDen) {
        boolean godkendt = false;
        if (givesDen.isAfter(super.getStartDen().minusDays(1)) && givesDen.isBefore(super.getSlutDen().plusDays(1))) {
            givetDato.add(givesDen);
            antalGangeGivet++;
            godkendt = true;
        }
        return godkendt;
    }

    public double getAntalEnheder() {
        return antalEnheder;
    }

    /** Returner antal gange ordinationen er anvendt. */
    public int getAntalGangeGivet() {
        return antalGangeGivet;
    }

    @Override
    public double samletDosis() {
        return antalGangeGivet * antalEnheder;
    }

    @Override
    public double doegnDosis() {
        return samletDosis() / super.antalDage();
    }

    @Override
    public String getType() {
        return this.getClass().getSimpleName();
    }
}
