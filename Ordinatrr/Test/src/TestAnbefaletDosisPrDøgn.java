import static org.junit.jupiter.api.Assertions.*;

import controller.Controller;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

import controller.Controller.*;
import ordination.*;
public class TestAnbefaletDosisPrDøgn {


    Lægemiddel ascetalisylsyre = new Lægemiddel(
            "Acetylsalicylsyre", 0.1, 0.15,
            0.16, "Styk");

    @Test //Grænseværdi: Vægt = 24
    void TC1(){
        Patient jane = new Patient("121256-0512", "Jane Jensen", 24);
        assertEquals(Controller.anbefaletDosisPrDoegn(jane, ascetalisylsyre), 0.1);
    }

    @Test //Grænseværdi: Vægt = 25
    void TC2(){
        Patient jane = new Patient("121256-0512", "Jane Jensen", 25);
        assertEquals(Controller.anbefaletDosisPrDoegn(jane, ascetalisylsyre), 0.15);
    }

    @Test //Basistilfælde: Vægt = 80
    void TC3(){
        Patient jane = new Patient("121256-0512", "Jane Jensen", 80);
        assertEquals(Controller.anbefaletDosisPrDoegn(jane, ascetalisylsyre), 0.15);
    }

    @Test //Grænseværdi: Vægt = 119
    void TC4(){
        Patient jane = new Patient("121256-0512", "Jane Jensen", 119);
        assertEquals(Controller.anbefaletDosisPrDoegn(jane, ascetalisylsyre), 0.15);
    }

    @Test //Grænseværdi: Vægt = 120
    void TC5(){
        Patient jane = new Patient("121256-0512", "Jane Jensen", 120);
        assertEquals(Controller.anbefaletDosisPrDoegn(jane, ascetalisylsyre), 0.16);
    }
}
