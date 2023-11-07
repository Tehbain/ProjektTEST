
import static org.junit.jupiter.api.Assertions.*;

import controller.Controller;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

import controller.Controller.*;
import ordination.*;

class TestOpretPNOrdination {

    LocalDate startDato = LocalDate.parse("2023-10-06");
    LocalDate slutDato = LocalDate.parse("2023-10-29");
    Patient jane = new Patient("121256-0512", "Jane Jensen", 63.4);
    Lægemiddel ascetalisylsyre = new Lægemiddel(
            "Acetylsalicylsyre", 0.1, 0.15,
            0.16, "Styk");
    double antal = 3;



    @Test // Basistilfælde
    void TC1(){
        PN createdPN = Controller.opretPNOrdination(startDato, slutDato, jane, ascetalisylsyre, antal);
        assertTrue(jane.getOrdinationer().contains(createdPN));
    }

    @Test // Omvendt dato
    void TC2(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Controller.opretPNOrdination(slutDato,startDato,jane,ascetalisylsyre,antal));
        assertEquals(exception.getMessage(), "Ugyldig dato.");
    }

    @Test // Patient = null
    void TC3() {
        PN createdPN = Controller.opretPNOrdination(startDato, slutDato, null, ascetalisylsyre, antal);
        assertInstanceOf(PN.class, createdPN);
    }

    @Test // Lægemiddel = null
    void TC4() {
        PN createdPN = Controller.opretPNOrdination(startDato, slutDato, jane, null, antal);
        assertTrue(jane.getOrdinationer().contains(createdPN));
    }

    @Test // Grænseværdig startDato = slutDato
    void TC5() {
        PN createdPN = Controller.opretPNOrdination(startDato, startDato, jane, ascetalisylsyre, antal);
        assertTrue(jane.getOrdinationer().contains(createdPN));
    }
}
