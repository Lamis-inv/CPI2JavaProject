package autoEcole.Repository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import autoEcole.Entities.Candidat;

public class CandidatRepository {
	 private final String filePath = "data/candidat.json";  // single candidate file
	  
	 private final Gson gson = new Gson();
	 private final Type listType = new TypeToken<List<Candidat>>(){}.getType();

	
	public void add(Candidat c) {
		List<Candidat> candidats = getAll();

        // Optional: check if CIN already exists
		for (Candidat ca : candidats) {
		    if (ca.getCin().equals(c.getCin())) {
		        System.out.println("Candidate with CIN " + c.getCin() + " already exists!");
		        return;
		    }
		}

        candidats.add(c);
        saveAll(candidats);
        System.out.println("Candidate added successfully!");
	}
	

	public List<Candidat> getAll() {
        try (FileReader reader = new FileReader(filePath)) {
            List<Candidat> candidats = gson.fromJson(reader, listType);
            return candidats != null ? candidats : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
	}
	
	public void findAll() {
	    List<Candidat> candidats = getAll(); // get the current list from JSON

	    if (candidats.isEmpty()) {
	        System.out.println("No candidates found.");
	        return;
	    }

	    for (Candidat c : candidats) {
	        System.out.println("-------------------------");
	        System.out.println("Nom: " + c.getNom());
	        System.out.println("Prenom: " + c.getPrenom());
	        System.out.println("Adresse: " + c.getAdresse());
	        System.out.println("Tel: " + c.getTelephone());
	        System.out.println("CIN: " + c.getCin());
	        System.out.println("Type Permis: " + c.getTypePermis());
	        System.out.println("Nb Seances Code: " + c.getNbSeanceCode());
	        System.out.println("Nb Seances Conduite: " + c.getNbSeanceConduite());
	    }
	}
	
	public void update(Candidat updated) {
        List<Candidat> candidats = getAll();
        for (int i = 0; i < candidats.size(); i++) {
            if (candidats.get(i).getCin().equals(updated.getCin())) {
                candidats.set(i, updated);
                saveAll(candidats);
                System.out.println("Candidate updated successfully!");
                return;
            }
        }
        System.out.println("Candidate not found!");
    }
	
	public void delete(String cin) {
        List<Candidat> candidats = getAll();
        boolean removed = candidats.removeIf(c -> c.getCin().equals(cin));
        if (removed) {
            saveAll(candidats);
            System.out.println("Candidate deleted successfully!");
        } else {
            System.out.println("Candidate not found!");
        }
    }
	private void saveAll(List<Candidat> candidats) {
	    try (FileWriter writer = new FileWriter(filePath)) {
	        gson.toJson(candidats, writer);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}
