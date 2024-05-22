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
    public CarMaintenance create(CarMaintenance maintenance) {
        return carMaintenanceRepository.save(maintenance);
    }

    @Override
    public CarMaintenance read(String maintenanceID) {
        return carMaintenanceRepository.findByMaintenanceID(maintenanceID).orElse(null);
    }

    @Override
    public CarMaintenance update(CarMaintenance maintenance) {
        return carMaintenanceRepository.save(maintenance);
    }

    @Override
    public void delete(String maintenanceID) {
        carMaintenanceRepository.deleteByMaintenanceID(maintenanceID);
    }

    @Override
    public List<CarMaintenance> getAll() {
        return carMaintenanceRepository.findAll();
    }
}
