package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.CarMaintenance;
import za.ac.cput.service.CarMaintenanceService;

import java.util.List;

@RestController
@RequestMapping("/car-maintenance")
public class CarMaintenanceController {

    @Autowired
    private CarMaintenanceService carMaintenanceService;

    @PostMapping("/save")
    public CarMaintenance save(@RequestBody CarMaintenance carMaintenance) {
        return carMaintenanceService.create(carMaintenance);
    }

    @GetMapping("/read/{maintenanceId}")
    public CarMaintenance read(@PathVariable String maintenanceId) {
        return carMaintenanceService.read(maintenanceId);
    }

    @PutMapping("/update")
    public CarMaintenance update(@RequestBody CarMaintenance carMaintenance) {
        return carMaintenanceService.update(carMaintenance);
    }

    @DeleteMapping("/delete/{maintenanceId}")
    public void delete(@PathVariable String maintenanceId) {
        carMaintenanceService.delete(maintenanceId);
    }

    @GetMapping("/get-all")
    public List<CarMaintenance> getAllCarMaintenance() {
        return carMaintenanceService.getAll();
    }
}

