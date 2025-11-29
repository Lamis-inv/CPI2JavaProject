package autoEcole.Repository;

import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import autoEcole.Entities.Comptabilite;

public class ComptabiliteRepository {
    private final String filePath = "data/comptabilite.json";
    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, (com.google.gson.JsonSerializer<LocalDate>) 
                (src, type, ctx) -> new com.google.gson.JsonPrimitive(src.toString()))
            .registerTypeAdapter(LocalDate.class, (com.google.gson.JsonDeserializer<LocalDate>) 
                (json, type, ctx) -> LocalDate.parse(json.getAsString()))
            .setPrettyPrinting().create();

    public void add(Comptabilite c) {
        Comptabilite[] all = getAll();
        Comptabilite[] newAll = Arrays.copyOf(all, all.length + 1);
        newAll[newAll.length - 1] = c;
        saveAll(newAll);
    }

    public Comptabilite[] getAll() {
        try (FileReader reader = new FileReader(filePath)) {
            Comptabilite[] data = gson.fromJson(reader, Comptabilite[].class);
            return data != null ? data : new Comptabilite[0];
        } catch (Exception e) { return new Comptabilite[0]; }
    }

    private void saveAll(Comptabilite[] list) {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(list, writer);
        } catch (Exception e) { e.printStackTrace(); }
    }
}
