package storage;

import java.util.ArrayList;
import java.util.List;

import ordination.Laegemiddel;
import ordination.Patient;

public class Storage {
    private final List<Patient> patienter = new ArrayList<>();
    private final List<Laegemiddel> laegemidler = new ArrayList<>();

    public List<Patient> getAllPatienter() {
        return new ArrayList<>(patienter);
    }

    public void storePatient(Patient patient) {
        patienter.add(patient);
    }

    public List<Laegemiddel> getAllLaegemidler() {
        return new ArrayList<>(laegemidler);
    }

    public void storeLaegemiddel(Laegemiddel laegemiddel) {
        laegemidler.add(laegemiddel);
    }
}
