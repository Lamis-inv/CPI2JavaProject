package autoEcole.Controller;

import autoEcole.Entities.Vehicule;
import autoEcole.Service.VehiculeService;

public class VehiculeController {

    VehiculeService vehiculeService = new VehiculeService();

    public void add(Vehicule v) {
        vehiculeService.addVehicule(v);
    }

    public void findAll() {
        vehiculeService.findAllVehicule();
    }

    public void update(String immatricule, Vehicule v) {
        vehiculeService.updateVehicule(immatricule, v);
    }

    public void delete(String immatricule) {
        vehiculeService.deleteVehicule(immatricule);
    }
}
