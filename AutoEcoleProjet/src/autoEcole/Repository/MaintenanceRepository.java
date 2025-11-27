package autoEcole.Repository;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.stream.Stream;
import com.google.gson.Gson;
import autoEcole.Entities.Maintenance;

public class MaintenanceRepository {
    private final String filePath = "data/maintenance.json";
    private final Gson gson = new Gson();

    public void ajouter(Maintenance m) {
        Maintenance[] all = getAll();
        Maintenance[] newTab = Arrays.copyOf(all, all.length + 1);
        newTab[newTab.length - 1] = m;
        saveAll(newTab);
        System.out.println("Maintenance ajoutée !");
    }

    public Maintenance[] getAll() {
        try (FileReader reader = new FileReader(filePath)) {
            Maintenance[] data = gson.fromJson(reader, Maintenance[].class);
            return data == null ? new Maintenance[0] : data;
        } catch (Exception e) {
            return new Maintenance[0];
        }
    }

    public void afficherParVehicule(String imm) {
        Maintenance[] list = getAll();
        Stream.of(list)
            .filter(m -> m.getImmatriculation().equals(imm))
            .forEach(m -> System.out.println(
                m.getDate() + " | " + m.getDescription() + " | " + m.getCout() + " DT"
            ));
    }

    private void saveAll(Maintenance[] data) {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(data, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
