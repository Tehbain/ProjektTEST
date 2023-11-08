import static org.junit.jupiter.api.Assertions.*;

import controller.Controller;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

import controller.Controller.*;
import ordination.*;
public class TestOrdinationPNAnvendt {

    LocalDate startDato = LocalDate.parse("2023-10-06");
    LocalDate slutDato = LocalDate.parse("2023-10-29");
    Patient jane = new Patient("121256-0512", "Jane Jensen", 63.4);
    Lægemiddel ascetalisylsyre = new Lægemiddel(
            "Acetylsalicylsyre", 0.1, 0.15,
            0.16, "Styk");
    double antal = 3;
    PN ordinationPN = Controller.opretPNOrdination(startDato,slutDato,jane,ascetalisylsyre,antal);


    @Test //Basistilfælde
    void TC1() {
        Controller.ordinationPNAnvendt(ordinationPN, LocalDate.parse("2023-10-10"));
        assertTrue(ordinationPN.getAntalGangeGivet() == 1);
    }

    @Test // Dato udenfor gyldigt interval, før start.
    void TC2() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Controller.ordinationPNAnvendt(ordinationPN,LocalDate.parse("2023-10-02")));
        assertEquals(exception.getMessage(), "Udenfor gyldighedsperiode.");
    }

    @Test // Dato udenfor gyldigt interval, efter slut.
    void TC3() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Controller.ordinationPNAnvendt(ordinationPN,LocalDate.parse("2023-11-12")));
        assertEquals(exception.getMessage(), "Udenfor gyldighedsperiode.");
    }

    @Test // Grænseværdi: datoGivet = startDato
    void TC4() {
        Controller.ordinationPNAnvendt(ordinationPN, LocalDate.parse("2023-10-06"));
        assertTrue(ordinationPN.getAntalGangeGivet() == 1);
    }

    @Test // Grænseværdi: datoGivet = slutDato
    void TC5() {
        Controller.ordinationPNAnvendt(ordinationPN, LocalDate.parse("2023-10-29"));
        assertTrue(ordinationPN.getAntalGangeGivet() == 1);
    }

    @Test // Dato = null
    void TC6() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Controller.ordinationPNAnvendt(ordinationPN, null));
        assertEquals(exception.getMessage(), "Udenfor gyldighedsperiode.");
    }
}
