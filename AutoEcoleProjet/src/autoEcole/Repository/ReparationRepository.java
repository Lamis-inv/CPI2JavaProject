package autoEcole.Repository;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.stream.Stream;
import com.google.gson.Gson;
import autoEcole.Entities.Reparation;

public class ReparationRepository {
    private final String filePath = "data/repairs.json";
    private final Gson gson = new Gson();

    public void ajouter(Reparation r) {
        Reparation[] all = getAll();
        Reparation[] newTab = Arrays.copyOf(all, all.length + 1);
        newTab[newTab.length - 1] = r;
        saveAll(newTab);
        System.out.println("Réparation ajoutée !");
    }

    public Reparation[] getAll() {
        try (FileReader reader = new FileReader(filePath)) {
            Reparation[] data = gson.fromJson(reader, Reparation[].class);
            return data == null ? new Reparation[0] : data;
        } catch (Exception e) {
            return new Reparation[0];
        }
    }

    public void afficherParVehicule(String imm) {
        Reparation[] list = getAll();
        Stream.of(list)
            .filter(r -> r.getImmatriculation().equals(imm))
            .forEach(r -> System.out.println(
                r.getDate() + " | " + r.getDescription() + " | " + r.getCout() + " DT"
            ));
    }

    private void saveAll(Reparation[] data) {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(data, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
