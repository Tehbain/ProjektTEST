import static controller.Controller.opretLaegemiddel;
import static org.junit.jupiter.api.Assertions.*;

import controller.Controller;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.LocalTime;

import controller.Controller.*;
import ordination.*;
public class TestOpretDagligSkævOrdination {

    LocalDate startDato = LocalDate.parse("2023-10-06");
    LocalDate slutDato = LocalDate.parse("2023-10-29");
    Patient jane = new Patient("121256-0512", "Jane Jensen", 63.4);
    Lægemiddel fucidin = new Lægemiddel(
            "Fucidin", 0.025, 0.025,
            0.025, "Styk");
    LocalTime[] klokkeSlet = new LocalTime[]{LocalTime.parse("09:15"), LocalTime.parse("15:45")};
    double[] antalEnheder = new double[]{1,2};

    @Test //Basistilfælde
    void TC1() {
        DagligSkæv createdDS = Controller.opretDagligSkaevOrdination(startDato,slutDato,jane,fucidin,klokkeSlet,antalEnheder);
        assertTrue(jane.getOrdinationer().contains(createdDS));
    }

    @Test //Omvendt Dato
    void TC2() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Controller.opretDagligSkaevOrdination(slutDato,startDato,jane,fucidin,klokkeSlet,antalEnheder));
        assertEquals(exception.getMessage(), "Ugyldig dato.");
    }

    @Test // Patient = null
    void TC3() {
        DagligSkæv createdDS = Controller.opretDagligSkaevOrdination(startDato,slutDato,null,fucidin,klokkeSlet,antalEnheder);
        assertInstanceOf(DagligSkæv.class, createdDS);
    }

    @Test //Lægemiddel = null
    void TC4(){
        DagligSkæv createdDS = Controller.opretDagligSkaevOrdination(startDato,slutDato,jane,null,klokkeSlet,antalEnheder);
        assertTrue(jane.getOrdinationer().contains(createdDS));
    }

    @Test // Grænseværdi: startDato = slutDato
    void TC5() {
        DagligSkæv createdDS = Controller.opretDagligSkaevOrdination(startDato,startDato,jane,fucidin,klokkeSlet,antalEnheder);
        assertTrue(jane.getOrdinationer().contains(createdDS));
    }

    @Test // KlokkeSlet/antalEnheder mismatch
    void TC6() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Controller.opretDagligSkaevOrdination(startDato,slutDato,jane,fucidin,klokkeSlet,new double[]{1,2,3}));
        assertEquals(exception.getMessage(), "Antal tidsbrikker og antal enheder stemmer ikke overens.");
    }

}