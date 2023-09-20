package ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/*
Ved daglig skæv forstås, at medicinen gives dagligt, i den periode ordinationen er gyldig. Med skæv menes,
at man ved ordinations oprettelse bestemmer, hvilke klokkeslæt medicinen skal gives på. Når ordinationen
oprettes, angives det, hvor meget og på hvilke tidspunkter af dagen patienten skal have medicinen. Her kan
angives et vilkårligt antal tidspunkter på døgnet, og for hvert tidspunkt angives antallet af enheder, patienten
skal have af den pågældende medicin.

Med nedenstående registrering skal patienten have medicinen 6 gange i døgnet på de angivne klokkeslæt:
    09:30   10:30   13:30   14:30   19:30   20:30
      2       1       2       1       2       1
 */

public class DagligSkæv extends Ordination {
    private ArrayList<Dosis> dosis = new ArrayList<>();

    public DagligSkæv(LocalDate startDen, LocalDate slutDen, Lægemiddel lægemiddel) {
        super(startDen, slutDen, lægemiddel);
    }

    public DagligSkæv(LocalDate startDen, LocalDate slutDen, Lægemiddel lægemiddel, List<Dosis> dosis) {
        super(startDen, slutDen, lægemiddel);
        this.dosis = (ArrayList<Dosis>) dosis;
    }

    public void opretDosis(LocalTime tid, double antal) {
        this.dosis.add( new Dosis(tid, antal));
    }

    @Override
    public double samletDosis() {
        int sum = 0;
        for (Dosis dosis : this.dosis) {
            sum += dosis.getAntal();
        }
        return sum * super.antalDage();
    }

    @Override
    public double doegnDosis() {
        return samletDosis() / antalDage();
    }

    @Override
    public String getType() {
        return this.getClass().getSimpleName();
    }
}
