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
import autoEcole.Entities.Vehicule;

public class VehiculeRepository {
	private final String filePath = "data/vehicule.json";
    private final Gson gson = new Gson();
	
    public void add(Vehicule v) {
		Vehicule[] vehicules = getAll();

        // Optional: check if Vehicule already exists
		for (Vehicule ve : vehicules) {
		    if (ve.getNumImmatricule()==v.getNumImmatricule()) {
		        System.out.println("Vehicule with number " + v.getNumImmatricule() + " already exists!");
		        return;
		    }
		}

		Vehicule[] newTab = Arrays.copyOf(vehicules, vehicules.length + 1);
	    newTab[newTab.length - 1] = v;

	    saveAll(newTab);
        System.out.println("Vehicule added successfully!");
	}
    
    public Vehicule[] getAll() {
	    try (FileReader reader = new FileReader(filePath)) {
	    	Vehicule[] data = gson.fromJson(reader, Vehicule[].class);
	        return data == null ? new Vehicule[0] : data;
	    } catch (Exception e) {
	        return new Vehicule[0];
	    }
	}
    
    public void findAll() {
    	Vehicule[] vehicules = getAll(); // get the current list from JSON

	    if (vehicules.length==0) {
	        System.out.println("No vehicules found.");
	        return;
	    }

	    for (Vehicule v : vehicules) {
	        System.out.println("-------------------------");
	        System.out.println("Numero Immatricule: " + v.getNumImmatricule() );
	        System.out.println("Date mise en service: " + v.getDateMiseEnService());
	        System.out.println("Kilometrage total: " + v.getKilometrageTotal());
	        System.out.println("Kilometrage avant entretien: " + v.getKmAvantEntretien());
	    }
	}
    
    public void update(int numImmatricule,Vehicule updated) {
    	Vehicule[] tab = getAll();
        for (int i = 0; i < tab.length; i++) {
        	if (tab[i].getNumImmatricule()==numImmatricule) {
                tab[i] = updated; 
                saveAll(tab);
                return;
            }
        }
        System.out.println("Vehicule not found!");
    }
    
    public void delete(int numImmatricule) {
    	Vehicule[] vehicules = getAll();
	    int count = 0;

	    for (Vehicule v : vehicules) {
	        if (!(v.getNumImmatricule() == numImmatricule)) {
	            count++;
	        }
	    }
	    if (count == vehicules.length) {
	        System.out.println("Vehicule not found!");
	        return;
	    }

	    Vehicule[] newTab = new Vehicule[count];
	    int index = 0;

	    for (Vehicule v : vehicules) {
	        if (!(v.getNumImmatricule() == numImmatricule)) {
	            newTab[index++] = v;
	        }
	    }

	    saveAll(newTab);
	}
    
    public void saveAll(Vehicule[] vehicules) {
	    try (FileWriter writer = new FileWriter(filePath)) {
	        gson.toJson(vehicules, writer);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
    
    
}
