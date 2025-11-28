package autoEcole.Repository;

import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import autoEcole.Entities.Vehicule;

public class VehiculeRepository {
    private final String filePath = "data/vehicule.json";
    private final Gson gson = new GsonBuilder()
 // LocalDate
    .registerTypeAdapter(LocalDate.class, (com.google.gson.JsonSerializer<LocalDate>) 
        (src, typeOfSrc, context) -> new com.google.gson.JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE)))
    .registerTypeAdapter(LocalDate.class, (com.google.gson.JsonDeserializer<LocalDate>) 
        (json, type, context) -> LocalDate.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE))
    // LocalTime
    .registerTypeAdapter(LocalTime.class, (com.google.gson.JsonSerializer<LocalTime>) 
        (src, typeOfSrc, context) -> new com.google.gson.JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_TIME)))
    .registerTypeAdapter(LocalTime.class, (com.google.gson.JsonDeserializer<LocalTime>) 
        (json, type, context) -> LocalTime.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_TIME))
    .setPrettyPrinting()
    .create();
    
    public void ajouter(Vehicule v) {
        Vehicule[] vehicules = getAll();
        for (Vehicule ve : vehicules) {
            if (ve.getNumImmatricule().equals(v.getNumImmatricule())) {
                System.out.println("Vehicule déjà existant !");
                return;
            }
        }
        Vehicule[] newTab = Arrays.copyOf(vehicules, vehicules.length + 1);
        newTab[newTab.length - 1] = v;
        saveAll(newTab);
        System.out.println("Vehicule ajouté avec succès !");
    }

    public Vehicule[] getAll() {
        try (FileReader reader = new FileReader(filePath)) {
            Vehicule[] data = gson.fromJson(reader, Vehicule[].class);
            return data == null ? new Vehicule[0] : data;
        } catch (Exception e) {
            return new Vehicule[0];
        }
    }

    public Vehicule findByImmatriculation(String imm) {
        for (Vehicule v : getAll()) {
            if (v.getNumImmatricule().equals(imm)) return v;
        }
        return null;
    }

    public void modifier(String imm, Vehicule updated) {
        Vehicule[] tab = getAll();
        for (int i = 0; i < tab.length; i++) {
            if (tab[i].getNumImmatricule().equals(imm)) {
                tab[i] = updated;
                saveAll(tab);
                System.out.println("Vehicule modifié !");
                return;
            }
        }
        System.out.println("Vehicule non trouvé !");
    }

    public void supprimer(String imm) {
        Vehicule[] vehicules = getAll();
        Vehicule[] newTab = Arrays.stream(vehicules)
            .filter(v -> !v.getNumImmatricule().equals(imm))
            .toArray(Vehicule[]::new);

        if (newTab.length == vehicules.length) {
            System.out.println("Vehicule non trouvé !");
            return;
        }

        saveAll(newTab);
        System.out.println("Vehicule supprimé !");
    }

    private void saveAll(Vehicule[] vehicules) {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(vehicules, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
