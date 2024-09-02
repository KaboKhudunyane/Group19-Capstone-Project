package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.CarInformation;
import za.ac.cput.service.BookingService;
import za.ac.cput.service.CarInformationService;
import za.ac.cput.service.CarInsuranceService;

import java.util.List;


@RestController
@RequestMapping("/api/carInformation")
@CrossOrigin
public class CarInformationController {

    private CarInsuranceService carInsuranceService;
    private CarInformationService carInformationService;

    @Autowired
    public CarInformationController(CarInsuranceService carInsuranceService, CarInformationService carInformationService) {
        this.carInformationService = carInformationService;
        this.carInsuranceService = carInsuranceService;

    }

    @PostMapping("/create")
    public CarInformation create(@RequestBody CarInformation carInformation){
        carInsuranceService.create(carInformation.getCarInsurance());
        return carInformationService.create(carInformation);
    }

    @GetMapping("/getall")
    public List<CarInformation> getAll(){
        return carInformationService.getAll();
    }

}



