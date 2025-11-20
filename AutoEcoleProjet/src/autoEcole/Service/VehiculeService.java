package autoEcole.Service;

import autoEcole.Entities.Vehicule;
import autoEcole.Repository.VehiculeRepository;

public class VehiculeService {

    VehiculeRepository vRepository = new VehiculeRepository();

    public void addVehicule(Vehicule v) {
        vRepository.add(v);
    }

    public void findAllVehicule() {
        vRepository.findAll();
    }

    public void updateVehicule(String immatricule, Vehicule v) {
        vRepository.update(immatricule, v);
    }

    public void deleteVehicule(String immatricule) {
        vRepository.delete(immatricule);
    }
}
