package autoEcole.Repository;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;

import com.google.gson.Gson;

import autoEcole.Entities.Vehicule;

public class VehiculeRepository {

    private final String filePath = "data/vehicule.json";
    private final Gson gson = new Gson();

    public void add(Vehicule v) {
        Vehicule[] vehicules = getAll();

        for (Vehicule ve : vehicules) {
            if (ve.getNumImmatricule().equals(v.getNumImmatricule())) {
                System.out.println("Vehicule already exists!");
                return;
            }
        }

        Vehicule[] newTab = Arrays.copyOf(vehicules, vehicules.length + 1);
        newTab[newTab.length - 1] = v;

        saveAll(newTab);
        System.out.println("Vehicule added successfully!");
    }

    public Vehicule[] getAll() {
        try (FileReader reader = new FileReader(filePath)) {
            Vehicule[] data = gson.fromJson(reader, Vehicule[].class);
            return data == null ? new Vehicule[0] : data;
        } catch (Exception e) {
            return new Vehicule[0];
        }
    }

    public void findAll() {
        Vehicule[] vehicules = getAll();

        if (vehicules.length == 0) {
            System.out.println("No vehicules found.");
            return;
        }

        for (Vehicule v : vehicules) {
            System.out.println("-------------------------");
            System.out.println("Immatricule: " + v.getNumImmatricule());
            System.out.println("Date mise en service: " + v.getDateMiseEnService());
            System.out.println("Kilometrage total: " + v.getKilometrageTotal());
            System.out.println("Km avant entretien: " + v.getKmAvantEntretien());
            System.out.println("Type: " + v.getType());
        }
    }

    public void update(String numImmatricule, Vehicule updated) {
        Vehicule[] tab = getAll();
        for (int i = 0; i < tab.length; i++) {
            if (tab[i].getNumImmatricule().equals(numImmatricule)) {
                tab[i] = updated;
                saveAll(tab);
                System.out.println("Vehicule updated!");
                return;
            }
        }
        System.out.println("Vehicule not found!");
    }

    public void delete(String numImmatricule) {
        Vehicule[] vehicules = getAll();

        long total = Arrays.stream(vehicules)
                .filter(v -> !v.getNumImmatricule().equals(numImmatricule))
                .count();

        if (total == vehicules.length) {
            System.out.println("Vehicule not found!");
            return;
        }

        Vehicule[] newTab = Arrays.stream(vehicules)
                .filter(v -> !v.getNumImmatricule().equals(numImmatricule))
                .toArray(Vehicule[]::new);

        saveAll(newTab);
        System.out.println("Vehicule deleted!");
    }

    private void saveAll(Vehicule[] vehicules) {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(vehicules, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
