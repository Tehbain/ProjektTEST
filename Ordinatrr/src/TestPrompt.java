import controller.Controller;
import ordination.Ordination;
import ordination.Patient;
import storage.Storage;

import java.time.LocalDate;

public class TestPrompt {
    public static void main(String[] args) {
        Storage storage = new Storage();
        Controller.setStorage(storage);
        Controller.initStorage();

        Patient jane = storage.getAllPatienter().get(0);
        System.out.println(jane);
        System.out.println(Controller.opretPNOrdination(LocalDate.now(), LocalDate.now().plusDays(2),jane, storage.getAllLaegemidler().get(0), 2));

        System.out.println(jane.getOrdinationer());

    }
}
