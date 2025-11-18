package autoEcole.Service;

import autoEcole.Entities.Vehicule;
import autoEcole.Repository.VehiculeRepository;

public class VehiculeService {
VehiculeRepository vRepository = new VehiculeRepository();
	
	public void addCandidat(Vehicule v) {
		Vehicule ve = new Vehicule(v.getNumImmatricule(), v.getDateMiseEnService(), v.getKilometrageTotal(), v.getKmAvantEntretien());
		vRepository.add(ve);
	}
	
	public void findAllVehicule() {
		vRepository.findAll();
	}
	
	public void updateVehicule(Vehicule v) {
		vRepository.update(0, v);
	}
}
