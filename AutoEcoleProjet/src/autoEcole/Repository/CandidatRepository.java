package autoEcole.Repository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import autoEcole.Entities.Candidat;
import autoEcole.Entities.Seance;

public class CandidatRepository {
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
	
	private final String filePath = "data/candidat.json";

	
	public void add(Candidat c) {
		Candidat[] candidats = getAll();

		for (Candidat ca : candidats) {
		    if (ca.getCin()==c.getCin()) {
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

	    if (candidats.length == 0) {
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
	        System.out.println("Total Price: " + c.getTotalPrice());
	        System.out.println("Paid Amount: " + c.getPaidAmount());
	        System.out.println("Remaining: " + c.getRemainingAmount());
	        System.out.println("Seances:");
	        SeanceRepository s =new SeanceRepository();
	        Seance[] tab=s.getAll();
	        for (int i = 0; i < tab.length; i++) {
	        	if (tab[i].getCandidat().getCin()==c.getCin()) {
	        		System.out.println("  - " + tab[i].getType() + " | " + tab[i].getDate()
	        		+ " " + tab[i].getHeure() + " | Prix: " + tab[i].getPrix());
	            }
	        }
	        System.out.println("Code Exam Passed : " + c.getCodeExamPassed());
		    System.out.println("Conduit Exam Passed : " + c.getConduitExamPassed());
	    }
	}

	public void update(int cin,Candidat updated) {
        Candidat[] tab = getAll();
        for (int i = 0; i < tab.length; i++) {
        	if (tab[i].getCin()==cin) {
                tab[i] = updated; 
                saveAll(tab);
                return;
            }
        }
        System.out.println("Candidate not found!");
    }
	
	public void delete(int cin) {
	    Candidat[] old = getAll();
	    int count = 0;

	    for (Candidat c : old) {
	        if (!(c.getCin()==cin)) {
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
	        if (!(c.getCin()==cin)) {
	            newTab[index++] = c;
	        }
	    }

	    saveAll(newTab);
	}
	public void findByCin(int cin) {
	    Candidat c = getByCin(cin);

	    if (c == null) {
	        System.out.println("Candidate with CIN " + cin + " not found!");
	        return;
	    }

	    System.out.println("-------------------------");
	    System.out.println("Nom: " + c.getNom());
	    System.out.println("Prenom: " + c.getPrenom());
	    System.out.println("Adresse: " + c.getAdresse());
	    System.out.println("Tel: " + c.getTelephone());
	    System.out.println("CIN: " + c.getCin());
	    System.out.println("Type Permis: " + c.getTypePermis());
	    System.out.println("Total Price: " + c.getTotalPrice());
	    System.out.println("Paid Amount: " + c.getPaidAmount());
	    System.out.println("Remaining: " + c.getRemainingAmount());
	    System.out.println("Seances:");
	    if (c.getSeances() != null) {
	        for (autoEcole.Entities.Seance s : c.getSeances()) {
	            System.out.println("  - " + s.getType() + " | " + s.getDate() + " " + s.getHeure() + " | Prix: " + s.getPrix());
	        }
	    }
	    System.out.println("Code Exam Passed : " + c.getCodeExamPassed());
	    System.out.println("Conduit Exam Passed : " + c.getConduitExamPassed());
	}


	public void saveAll(Candidat[] candidats) {
	    try (FileWriter writer = new FileWriter(filePath)) {
	        gson.toJson(candidats, writer);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public Candidat getByCin(int cin) {
	    Candidat[] tab = getAll();
	    for (Candidat c : tab) {
	        if (c.getCin() == cin) return c;
	    }
	    return null;
	}


}
