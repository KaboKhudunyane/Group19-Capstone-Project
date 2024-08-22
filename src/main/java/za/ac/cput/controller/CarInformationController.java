package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.CarInformation;
import za.ac.cput.service.CarInformationService;

import java.util.List;


@RestController
@RequestMapping("/api/carInformation")
@CrossOrigin
public class CarInformationController {

    @Autowired
    private CarInformationService carInformationService;

    @PostMapping("/create")
    public CarInformation create(@RequestBody CarInformation carInformation){
        return carInformationService.create(carInformation);
    }

    @GetMapping("/getall")
    public List<CarInformation> getAll(){
        return carInformationService.getAll();
    }

}



