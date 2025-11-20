package autoEcole.Repository;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;

import com.google.gson.Gson;

import autoEcole.Entities.Candidat;
import autoEcole.Entities.Seance;

public class SeanceRepository {

	private final String filePath = "data/seance.json";
    private final Gson gson = new Gson();
    
    public void add(Seance s) {
    	Seance[] seances = getAll();

		for (Seance ca : seances) {
		    if (ca.getId()==s.getId()) {
		        System.out.println("La seance " + s.getId() + " already exists!");
		        return;
		    }
		}

		Seance[] newTab = Arrays.copyOf(seances, seances.length + 1);
	    newTab[newTab.length - 1] = s;

	    saveAll(newTab);
        System.out.println("Seance added successfully!");
	}
    
    public Seance[] getAll() {
	    try (FileReader reader = new FileReader(filePath)) {
	    	Seance[] data = gson.fromJson(reader, Seance[].class);
	        return data == null ? new Seance[0] : data;
	    } catch (Exception e) {
	        return new Seance[0];
	    }
	}
    
    public void findAll() {
    	Seance[] seances = getAll(); // get the current list from JSON

	    if (seances.length==0) {
	        System.out.println("No seances found.");
	        return;
	    }

	    for (Seance c : seances) {
	        System.out.println("-------------------------");
	        System.out.println("ID: " + c.getId());
	        System.out.println("Type: " + c.getType());
	        System.out.println("Date: " + c.getDate());
	        System.out.println("Heure: " + c.getHeure());
	        System.out.println("Moniteur: " + c.getMoniteur());
	        System.out.println("Candidat: " + c.getCandidat());
	        System.out.println("Vehicule: " + c.getVehicule());
	        System.out.println("Prix: " + c.getPrix());
	        
	    }
	}
    
    public void update(int id,Seance updated) {
    	Seance[] tab = getAll();
        for (int i = 0; i < tab.length; i++) {
        	if (tab[i].getId()==id) {
                tab[i] = updated; 
                saveAll(tab);
                return;
            }
        }
        System.out.println("Seance not found!");
    }
    
    public void delete(int id) {
    	Seance[] old = getAll();
	    int count = 0;

	    for (Seance c : old) {
	        if (!(c.getId()==id)) {
	            count++;
	        }
	    }

	    if (count == old.length) {
	        System.out.println("Seance not found!");
	        return;
	    }

	    Seance[] newTab = new Seance[count];
	    int index = 0;

	    for (Seance c : old) {
	        if (!(c.getId()==id)) {
	            newTab[index++] = c;
	        }
	    }

	    saveAll(newTab);
	}
    
    public void findById(int id) {
    	Seance[] seances = getAll();

	    for (Seance c : seances) {
	        if (c.getId() == id) {
	            System.out.println("-------------------------");
	            System.out.println("ID: " + c.getId());
		        System.out.println("Type: " + c.getType());
		        System.out.println("Date: " + c.getDate());
		        System.out.println("Heure: " + c.getHeure());
		        System.out.println("Moniteur: " + c.getMoniteur().getNom());
		        System.out.println("Candidat: " + c.getCandidat().getNom()+" "+c.getCandidat().getPrenom());
		        System.out.println("Vehicule: " + c.getVehicule().getNumImmatricule());
		        System.out.println("Prix: " + c.getPrix());
	            return; // Stop after printing one
	        }
	    }

	    System.out.println("Seance with ID " + id + " not found!");
	}
    
    public void saveAll(Seance[] seances) {
	    try (FileWriter writer = new FileWriter(filePath)) {
	        gson.toJson(seances, writer);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
    public Seance getById(int id) {
    	Seance[] tab = getAll();
	    for (Seance c : tab) {
	        if (c.getId() == id) return c;
	    }
	    return null;
	}
}
