package autoEcole.Repository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import autoEcole.Entities.Candidat;

public class CandidatRepository {
	private final String filePath = "data/candidat.json";
    private final Gson gson = new Gson();

	
	public void add(Candidat c) {
		Candidat[] candidats = getAll();

        // Optional: check if CIN already exists
		for (Candidat ca : candidats) {
		    if (ca.getCin().equals(c.getCin())) {
		        System.out.println("Candidate with CIN " + c.getCin() + " already exists!");
		        return;
		    }
		}

		Candidat[] newTab = Arrays.copyOf(candidats, candidats.length + 1);
	    newTab[newTab.length - 1] = c;

	    saveAll(newTab);
        System.out.println("Candidate added successfully!");
	}
	

	public Candidat[] getAll() {
	    try (FileReader reader = new FileReader(filePath)) {
	        Candidat[] data = gson.fromJson(reader, Candidat[].class);
	        return data == null ? new Candidat[0] : data;
	    } catch (Exception e) {
	        return new Candidat[0];
	    }
	}

	
	public void findAll() {
	    Candidat[] candidats = getAll(); // get the current list from JSON

	    if (candidats.length==0) {
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
	
	public void update(String cin,Candidat updated) {
        Candidat[] tab = getAll();
        for (int i = 0; i < tab.length; i++) {
        	if (tab[i].getCin().equals(cin)) {
                tab[i] = updated; 
                saveAll(tab);
                return;
            }
        }
        System.out.println("Candidate not found!");
    }
	
	public void delete(String cin) {
	    Candidat[] old = getAll();
	    int count = 0;

	    for (Candidat c : old) {
	        if (!c.getCin().equals(cin)) {
	            count++;
	        }
	    }

	    if (count == old.length) {
	        System.out.println("Candidate not found!");
	        return;
	    }

	    Candidat[] newTab = new Candidat[count];
	    int index = 0;

	    for (Candidat c : old) {
	        if (!c.getCin().equals(cin)) {
	            newTab[index++] = c;
	        }
	    }

	    saveAll(newTab);
	}
	public void saveAll(Candidat[] candidats) {
	    try (FileWriter writer = new FileWriter(filePath)) {
	        gson.toJson(candidats, writer);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
