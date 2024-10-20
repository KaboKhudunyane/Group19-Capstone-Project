package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.CarInsurance;
import za.ac.cput.service.CarInsuranceService;

import java.util.List;

@RestController
@RequestMapping("/api/carInsurance")
@CrossOrigin(origins = "http://localhost:5173")
public class CarInsuranceController {

    private final CarInsuranceService carInsuranceService;

    @Autowired
    public CarInsuranceController(CarInsuranceService carInsuranceService) {
        this.carInsuranceService = carInsuranceService;
    }

    @PostMapping("/create")
    public CarInsurance create(@RequestBody CarInsurance carInsurance) {
        System.out.println("Received Car Insurance: " + carInsurance);
        return carInsuranceService.create(carInsurance);
    }


    @GetMapping("/get/{carInsuranceID}")
    public CarInsurance read(@PathVariable Long carInsuranceID) {
        return carInsuranceService.read(carInsuranceID);
    }

    @PutMapping("/update")
    public CarInsurance update(@RequestBody CarInsurance carInsurance) {
        return carInsuranceService.update(carInsurance);
    }

    @DeleteMapping("/delete/{carInsuranceID}")
    public void delete(@PathVariable Long carInsuranceID) {
        carInsuranceService.delete(carInsuranceID);
    }

    @GetMapping("/getall")
    public List<CarInsurance> getAll() {
        return carInsuranceService.getAll();
    }
}

