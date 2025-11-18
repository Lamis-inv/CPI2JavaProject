package autoEcole.Repository;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;

import com.google.gson.Gson;

import autoEcole.Entities.Candidat;
import autoEcole.Entities.Moniteur;

	public class MoniteurRepository {
		private final String filePath = "data/moniteur.json";
	    private final Gson gson = new Gson();
	    
	    
	    public void add(Moniteur m) {
			Moniteur[] moniteurs = getAll();
	
			for (Moniteur ca : moniteurs) {
			    if (ca.getId()== ca.getId() ) {
			        System.out.println("Candidate with CIN " + m.getId() + " already exists!");
			        return;
			    }
			}
	
			Moniteur[] newTab = Arrays.copyOf(moniteurs, moniteurs.length + 1);
		    newTab[newTab.length - 1] = m;
	
		    saveAll(newTab);
	        System.out.println("Moniteur added successfully!");
		}
    
    public Moniteur[] getAll() {
	    try (FileReader reader = new FileReader(filePath)) {
	        Moniteur[] data = gson.fromJson(reader, Moniteur[].class);
	        return data == null ? new Moniteur[0] : data;
	    } catch (Exception e) {
	        return new Moniteur[0];
	    }
	}
    public void findAll() {
	    Moniteur[] moniteurs = getAll();

	    if (moniteurs.length==0) {
	        System.out.println("No candidates found.");
	        return;
	    }

	    for (Moniteur m : moniteurs) {
	        System.out.println("-------------------------");
	        System.out.println("Nom: " + m.getNom());	      
	        System.out.println("ID: " + m.getId());
	        System.out.println("Disponibilite: " + m.isDisponible());
	        System.out.println("Nb Heures Travaillees: " + m.getNbHeuresTravaillees());
	    }
	}
    
    public void update(int id,Moniteur updated) {
        Moniteur[] tab = getAll();
        for (int i = 0; i < tab.length; i++) {
        	if (tab[i].getId()==id) {
                tab[i] = updated; 
                saveAll(tab);
                return;
            }
        }
        System.out.println("Candidate not found!");
    }
    
    
    public void delete(int id) {
	    Moniteur[] old = getAll();
	    int count = 0;

	    for (Moniteur c : old) {
	        if (!(c.getId()==id)) {
	            count++;
	        }
	    }

	    if (count == old.length) {
	        System.out.println("Candidate not found!");
	        return;
	    }

	    Moniteur[] newTab = new Moniteur[count];
	    int index = 0;

	    for (Moniteur c : old) {
	        if (!(c.getId()==id)) {
	            newTab[index++] = c;
	        }
	    }
	    saveAll(newTab);
	}
    public void saveAll(Moniteur[] moniteurs) {
	    try (FileWriter writer = new FileWriter(filePath)) {
	        gson.toJson(moniteurs, writer);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
    
}
