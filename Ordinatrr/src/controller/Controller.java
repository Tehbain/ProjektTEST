package controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import ordination.*;
import storage.Storage;

public abstract class Controller {
    private static Storage storage;

    public static void setStorage(Storage storage) {
        Controller.storage = storage;
    }


    /**
     * Opret og returner en PN ordination.
     * Hvis startDato er efter slutDato, kastes en IllegalArgumentException,
     * og ordinationen oprettes ikke.
     * Pre: antal > 0.
     */
    public static PN opretPNOrdination(LocalDate startDato, LocalDate slutDato, Patient patient, Lægemiddel lægemiddel, double antal) {
        try {
            if (startDato.isAfter(slutDato)) {
                throw new IllegalArgumentException("Ugyldig dato");
            } else {
                PN PNret =  new PN(startDato,slutDato,lægemiddel);
                patient.addOrdination(PNret);
                // TODO storage skal gemme patienten?
                return PNret;

            }
        }catch (IllegalArgumentException e){
        }

        return null;
    }

    /**
     * Opret og returner en DagligFast ordination.
     * Hvis startDato er efter slutDato, kastes en IllegalArgumentException,
     * og ordinationen oprettes ikke.
     * Pre: morgenAntal, middagAntal, aftenAntal, natAntal >= 0
     */
    public static DagligFast opretDagligFastOrdination(
            LocalDate startDato, LocalDate slutDato, Patient patient, Lægemiddel lægemiddel,
            double morgenAntal, double middagAntal, double aftenAntal, double natAntal) {

        try {
            if (startDato.isAfter(slutDato)) {
                throw new IllegalArgumentException("Du lader til at have problemer. Prøv igen.");
            } else {
                DagligFast DFret = new DagligFast(startDato,slutDato, lægemiddel);
                patient.addOrdination(DFret);
                return DFret;
            }

        } catch (IllegalArgumentException e) {
        }

        return null;
    }

    /**
     * Opret og returner en DagligSkæv ordination.
     * Hvis startDato er efter slutDato, kastes en IllegalArgumentException,
     * og ordinationen oprettes ikke.
     * Hvis antallet af elementer i klokkeSlet og antalEnheder er forskellige,
     * kastes en IllegalArgumentException.
     * Pre: I antalEnheder er alle tal >= 0.
     */
    public static DagligSkæv opretDagligSkaevOrdination(
            LocalDate startDen, LocalDate slutDen, Patient patient, Lægemiddel lægemiddel,
            LocalTime[] klokkeSlet, double[] antalEnheder) {

        try {

            if (startDen.isAfter(slutDen)) {
                throw new IllegalArgumentException("Vi må simpelthen holde op med at mødes på den her måde");
            }
            if (klokkeSlet.length != antalEnheder.length) {
                throw new IllegalArgumentException("Nope");
            }
            else {
                DagligSkæv DSret = new DagligSkæv(startDen,slutDen,lægemiddel);
                patient.addOrdination(DSret);
                return DSret;
            }

        }catch (IllegalArgumentException e) {

        }

        return null;
    }

    /**
     * Tilføj en dato for anvendelse af PN ordinationen.
     * Hvis datoen ikke er indenfor ordinationens gyldighedsperiode,
     * kastes en IllegalArgumentException.
     */
    public static void ordinationPNAnvendt(PN ordination, LocalDate dato) {
        try {

            if (dato.isBefore(ordination.getStartDen()) || dato.isAfter(ordination.getSlutDen())) {
                throw new IllegalArgumentException("Det kan du godt gøre bedre, ik'?");
            } else {
                ordination.givDosis(dato);
            }

        }catch (IllegalArgumentException e) {

        }
    }

    /**
     * Returner den anbefalede dosis pr. døgn af lægemidlet til patienten
     * (afhænger af patientens vægt).
     */
    public static double anbefaletDosisPrDoegn(Patient patient, Lægemiddel lægemiddel) {

        double anbefalet = 0;
        double vægt = patient.getVægt();

        if (vægt < 25) {
            return lægemiddel.getEnhedPrKgPrDoegnLet();
        } else if (vægt > 120) {
            return lægemiddel.getEnhedPrKgPrDoegnTung();
        } else return lægemiddel.getEnhedPrKgPrDoegnNormal();


    }

    /** Returner antal ordinationer for det givne vægtinterval og det givne lægemiddel. */
    public static int antalOrdinationerPrVaegtPrLaegemiddel(
            double vaegtStart, double vaegtSlut, Lægemiddel lægemiddel) {

        int countOrdinationer = 0;

        ArrayList<Patient> patienter = new ArrayList<>(storage.getAllPatienter());

        for (Patient patient : patienter) {
            for (Ordination ordination : patient.getOrdinationer()) {
                if (patient.getVægt() > vaegtStart && patient.getVægt() < vaegtSlut
                        && ordination.getLægemiddel().equals(lægemiddel)) {
                    countOrdinationer++;
                }
            }
        }

        return countOrdinationer;
    }

    public static List<Patient> getAllPatienter() {
        return storage.getAllPatienter();
    }

    public static List<Lægemiddel> getAllLaegemidler() {
        return storage.getAllLaegemidler();
    }

    public static Patient opretPatient(String cpr, String navn, double vaegt) {
        Patient p = new Patient(cpr, navn, vaegt);
        storage.storePatient(p);
        return p;
    }

    public static Lægemiddel opretLaegemiddel(
            String navn, double enhedPrKgPrDoegnLet, double enhedPrKgPrDoegnNormal,
            double enhedPrKgPrDoegnTung, String enhed) {
        Lægemiddel lm = new Lægemiddel(navn, enhedPrKgPrDoegnLet,
                enhedPrKgPrDoegnNormal, enhedPrKgPrDoegnTung, enhed);
        storage.storeLaegemiddel(lm);
        return lm;
    }

    public static void initStorage() {
        Patient jane = opretPatient("121256-0512", "Jane Jensen", 63.4);
        Patient finn = opretPatient("070985-1153", "Finn Madsen", 83.2);
        opretPatient("050972-1233", "Hans Jørgensen", 89.4);
        opretPatient("011064-1522", "Ulla Nielsen", 59.9);
        Patient ib = opretPatient("090149-2529", "Ib Hansen", 87.7);

        Lægemiddel acetylsalicylsyre = opretLaegemiddel(
                "Acetylsalicylsyre", 0.1, 0.15,
                0.16, "Styk");
        Lægemiddel paracetamol = opretLaegemiddel(
                "Paracetamol", 1, 1.5,
                2, "Ml");
        Lægemiddel fucidin = opretLaegemiddel(
                "Fucidin", 0.025, 0.025,
                0.025, "Styk");
        opretLaegemiddel(
                "Methotrexate", 0.01, 0.015,
                0.02, "Styk");

        opretPNOrdination(LocalDate.parse("2019-01-01"), LocalDate.parse("2019-01-12"),
                jane, paracetamol, 123);

        opretPNOrdination(LocalDate.parse("2019-02-12"), LocalDate.parse("2019-02-14"),
                jane, acetylsalicylsyre, 3);

        opretPNOrdination(LocalDate.parse("2019-01-20"), LocalDate.parse("2019-01-25"),
                ib, fucidin, 5);

        opretPNOrdination(LocalDate.parse("2019-01-01"), LocalDate.parse("2019-01-12"),
                jane, paracetamol, 123);

        opretDagligFastOrdination(LocalDate.parse("2019-01-10"), LocalDate.parse("2019-01-12"),
                finn, fucidin, 2, 0, 1, 0);

        LocalTime[] kl = {
                LocalTime.parse("12:00"), LocalTime.parse("12:40"),
                LocalTime.parse("16:00"), LocalTime.parse("18:45")};
        double[] an = {0.5, 1, 2.5, 3};
        opretDagligSkaevOrdination(LocalDate.parse("2019-01-23"), LocalDate.parse("2019-01-24"),
                finn, fucidin, kl, an);
    }
}