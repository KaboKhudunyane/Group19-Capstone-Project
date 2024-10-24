package za.ac.cput.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.CarInformation;
import za.ac.cput.domain.CarInsurance;
import za.ac.cput.service.CarInformationService;
import za.ac.cput.service.CarInsuranceService;
import za.ac.cput.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/carInsurance")
@CrossOrigin
public class CarInsuranceController {

    @Autowired
    CarInsuranceService carInsuranceService;

    @Autowired
    UserService userService;

    @Autowired
    CarInformationService carInformationService;

    @PostMapping("/create")
    public CarInsurance create(@RequestBody CarInsurance carInsurance){
        carInformationService.read(carInsurance.getUser().getUserID());
        userService.read(carInsurance.getUser().getUserID());
        return carInsuranceService.create(carInsurance);
    }

    @GetMapping("/getall")
    public List<CarInsurance> getAll() {
        return carInsuranceService.getAll();
    }



    @GetMapping("/insurance/{userID}")
    public List<CarInsurance> getInsuranceUserId(@PathVariable Long userID) {
        return carInsuranceService.getCarInsuranceByUser(userID);
    }


}
