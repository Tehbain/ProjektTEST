import static controller.Controller.opretDagligFastOrdination;
import static controller.Controller.opretPNOrdination;
import static org.junit.jupiter.api.Assertions.*;

import controller.Controller;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

import controller.Controller.*;
import ordination.*;
import storage.Storage;

public class TestAntalOrdinationerPrVægtPrLægemiddel {

    Storage storage = new Storage();
    Lægemiddel ascetalisylsyre = new Lægemiddel(
            "Acetylsalicylsyre", 0.1, 0.15,
            0.16, "Styk");

    @BeforeEach
    void setUp() {

        Controller.setStorage(storage);

        Patient ulla = Controller.opretPatient("112233-4455", "Ulla Nielsen", 59.9);
        Patient hans = Controller.opretPatient("667788-9900", "Hans Jørgensen", 89.4);

        PN pn = opretPNOrdination(LocalDate.parse("2019-01-01"), LocalDate.parse("2019-01-12"),
                ulla, ascetalisylsyre, 123);
        PN pn1 = opretPNOrdination(LocalDate.parse("2019-02-12"), LocalDate.parse("2019-02-14"),
                ulla, ascetalisylsyre, 3);

        PN pn2 = opretPNOrdination(LocalDate.parse("2019-01-20"), LocalDate.parse("2019-01-25"),
                hans, ascetalisylsyre, 5);
        PN pn3 = opretPNOrdination(LocalDate.parse("2019-01-01"), LocalDate.parse("2019-01-12"),
                hans, ascetalisylsyre, 123);
        DagligFast dagligFast = opretDagligFastOrdination(LocalDate.parse("2019-01-10"), LocalDate.parse("2019-01-12"),
                hans, ascetalisylsyre, 2, 0, 1, 0);
    }


    @Test
    void TC1() { //Fisker efter kun Ulla
        assertEquals(2, Controller.antalOrdinationerPrVaegtPrLaegemiddel(24,80, ascetalisylsyre));
    }

    @Test //Fisker efter både Ulla og Hans
    void TC2(){
        assertEquals(5, Controller.antalOrdinationerPrVaegtPrLaegemiddel(24,100, ascetalisylsyre));
    }

    @Test //Fisker efter kun Hans
    void TC3() {
        assertEquals(3, Controller.antalOrdinationerPrVaegtPrLaegemiddel(80,100, ascetalisylsyre));
    }

    @Test //Fisker efter ingen. Søgeinterval mindre end Ulla (59.9)
    void TC4() {
        assertEquals(0, Controller.antalOrdinationerPrVaegtPrLaegemiddel(24,55, ascetalisylsyre));
    }

    @Test //Fisker efter ingen. Søgeinterval større end Hans (89.4)
    void TC5() {
        assertEquals(0, Controller.antalOrdinationerPrVaegtPrLaegemiddel(90,120, ascetalisylsyre));
    }
}
