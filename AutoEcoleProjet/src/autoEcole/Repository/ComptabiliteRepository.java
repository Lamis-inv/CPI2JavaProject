package autoEcole.Repository;

import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import autoEcole.Entities.Comptabilite;

public class ComptabiliteRepository {

    private final String filePath = "data/comptabilite.json";

    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, (com.google.gson.JsonSerializer<LocalDate>)
                    (src, type, ctx) -> new com.google.gson.JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE)))
            .registerTypeAdapter(LocalDate.class, (com.google.gson.JsonDeserializer<LocalDate>)
                    (json, type, ctx) -> LocalDate.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE))
            .setPrettyPrinting()
            .create();

    public void add(Comptabilite c) {
        Comptabilite[] list = getAll();
        Comptabilite[] newList = Arrays.copyOf(list, list.length + 1);
        newList[newList.length - 1] = c;
        saveAll(newList);
    }

    public Comptabilite[] getAll() {
        try (FileReader reader = new FileReader(filePath)) {
            Comptabilite[] data = gson.fromJson(reader, Comptabilite[].class);
            return data == null ? new Comptabilite[0] : data;
        } catch (Exception e) {
            return new Comptabilite[0];
        }
    }

    private void saveAll(Comptabilite[] list) {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(list, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
