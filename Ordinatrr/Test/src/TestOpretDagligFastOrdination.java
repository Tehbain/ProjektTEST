import static org.junit.jupiter.api.Assertions.*;

import controller.Controller;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

import controller.Controller.*;
import ordination.*;
public class TestOpretDagligFastOrdination {

    LocalDate startDato = LocalDate.parse("2023-10-06");
    LocalDate slutDato = LocalDate.parse("2023-10-29");
    Patient jane = new Patient("121256-0512", "Jane Jensen", 63.4);
    Lægemiddel ascetalisylsyre = new Lægemiddel(
            "Acetylsalicylsyre", 0.1, 0.15,
            0.16, "Styk");
    double morgenAntal = 2;
    double middagAntal = 0;
    double aftenAntal = 1;
    double natAntal = 2;

    @Test //Basistilfælde
    void TC1() {
        DagligFast createdDF = Controller.opretDagligFastOrdination(startDato,slutDato,jane,ascetalisylsyre,morgenAntal,middagAntal,aftenAntal,natAntal);
        assertTrue(jane.getOrdinationer().contains(createdDF));
    }

    @Test // Ovendt dato
    void TC2() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Controller.opretDagligFastOrdination(slutDato,startDato,jane,ascetalisylsyre,morgenAntal,middagAntal,aftenAntal,natAntal));
        assertEquals(exception.getMessage(), "Ugyldig dato.");
    }

    @Test // Patient = null
    void TC3() {
        DagligFast createdDF = Controller.opretDagligFastOrdination(startDato,slutDato,null,ascetalisylsyre,morgenAntal,middagAntal,aftenAntal,natAntal);
        assertInstanceOf(DagligFast.class, createdDF);
    }

    @Test // Lægemiddel = null
    void TC4() {
        DagligFast createdDF = Controller.opretDagligFastOrdination(startDato,slutDato,jane,null,morgenAntal,middagAntal,aftenAntal,natAntal);
        assertTrue(jane.getOrdinationer().contains(createdDF));
    }

    @Test // Grænseværdi: startDato = slutDato
    void TC5() {
        DagligFast createdDF = Controller.opretDagligFastOrdination(startDato,startDato,jane,ascetalisylsyre,morgenAntal,middagAntal,aftenAntal,natAntal);
        assertTrue(jane.getOrdinationer().contains(createdDF));
    }

}
