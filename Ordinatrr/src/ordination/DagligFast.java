package ordination;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
Ved daglig fast forstås, at medicinen gives dagligt, i den periode ordinationen er gyldig.
Med fast menes, at medicinen højst gives 4 gange i døgnet på forud bestemte tidspunkter (Morgen, middag, aften og nat).

    For hvert af disse tidspunkter registreres, hvor mange enheder patienten skal have af den pågældende medicin.
    Med nedenstående eksempel på en registrering skal patienten have medicinen morgen, middag og nat, og der
    gives hhv. 2, 1 og 1 enhed af medicinen.
                        Morgen Middag Aften Nat
                          2       1     0    1
 */
public class DagligFast extends Ordination {
    /** Ass. 0..4 Dosis */
    private Dosis[] dosis = new Dosis[4];

    public DagligFast(LocalDate startDen, LocalDate slutDen, Lægemiddel lægemiddel) {
        super(startDen, slutDen, lægemiddel);
    }
    public DagligFast(LocalDate startDen, LocalDate slutDen, Lægemiddel lægemiddel, Dosis[] dosis) {
        super(startDen, slutDen, lægemiddel);
        this.dosis = dosis;
    }

    public void addDosis(Dosis dosis, int MorMiAftNat) {
        this.dosis[MorMiAftNat] = dosis;
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
