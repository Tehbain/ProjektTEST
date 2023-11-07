import controller.Controller;
import ordination.Ordination;
import ordination.Patient;
import storage.Storage;

import java.time.LocalDate;
import java.time.LocalTime;

public class TestPrompt {
    public static void main(String[] args) {
        Storage storage = new Storage();
        Controller.setStorage(storage);
        Controller.initStorage();

        System.out.println("TEST, JANE");
        Patient jane = storage.getAllPatienter().get(0);
        System.out.println(jane);
        System.out.println("OPRETTER NY PN");
        System.out.println(Controller.opretPNOrdination(LocalDate.now(), LocalDate.now().plusDays(2),jane, storage.getAllLaegemidler().get(0), 2));
        System.out.println(jane.getOrdinationer());
        System.out.println("OPRETTER NY FAST");
        System.out.println(Controller.opretDagligFastOrdination(LocalDate.now(), LocalDate.now().plusDays(3), jane, storage.getAllLaegemidler().get(1),1,2,3,4));
        System.out.println(jane.getOrdinationer());
        System.out.println("OPRETTER NY SKÆV");
        LocalTime[] dage = new LocalTime[]{LocalTime.now(), LocalTime.now().plusHours(48)};
        double[] antalPrDag = new double[]{1,3};
        System.out.println(Controller.opretDagligSkaevOrdination(LocalDate.now(), LocalDate.now().plusDays(4), jane, storage.getAllLaegemidler().get(2),dage,antalPrDag));
        System.out.println(jane.getOrdinationer());


        System.out.println("BREH: " + Controller.opretDagligFastOrdination(LocalDate.now().plusDays(3),LocalDate.now() , jane, storage.getAllLaegemidler().get(1),1,2,3,4));

    }
}
