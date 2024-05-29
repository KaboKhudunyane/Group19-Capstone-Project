package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.CarMaintenance;
import za.ac.cput.repository.CarMaintenanceRepository;

import java.util.List;

@Service
public class CarMaintenanceService implements IService<CarMaintenance, String> {
    private CarMaintenanceRepository carMaintenanceRepository;

    @Autowired
    CarMaintenanceService(CarMaintenanceRepository carMaintenanceRepository) {
        this.carMaintenanceRepository = carMaintenanceRepository;
    }
    @Override
    public CarMaintenance create(CarMaintenance carMaintenance) {
        return carMaintenanceRepository.save(carMaintenance);
    }
    @Override
    public CarMaintenance read(String carMaintenanceID) {
        return carMaintenanceRepository.findByCarMaintenanceID(carMaintenanceID);
    }
    @Override
    public CarMaintenance update(CarMaintenance carMaintenance) {
        return carMaintenanceRepository.save(carMaintenance);
    }
    public void delete(String carMaintenanceID) {
        carMaintenanceRepository.deleteByCarMaintenanceID(carMaintenanceID);
    }
     public List<CarMaintenance> getAllCarMaintenances() {
        return carMaintenanceRepository.getAllCarMaintainences();
    }
}
