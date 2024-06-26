package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.CarInsurance;
import za.ac.cput.domain.SupportTicket;
import za.ac.cput.service.CarInsuranceService;

import java.util.List;

@RestController
@RequestMapping("/carInsurance")
public class CarInsuranceController {

    @Autowired
    private CarInsuranceService carInsuranceService;

    @PostMapping("/create")
    public CarInsurance create(@RequestBody CarInsurance carInsurance){return carInsuranceService.create(carInsurance);}

    @GetMapping("/read/{carInsuranceID}")
    public CarInsurance read(@PathVariable String carInsuranceID){return carInsuranceService.read(carInsuranceID);}

    @PutMapping("/update")
    public CarInsurance update(@RequestBody CarInsurance carInsurance){return carInsuranceService.update(carInsurance);}

    @DeleteMapping("/delete/{carInsuranceID}")
    public void delete(@PathVariable String carInsuranceID){carInsuranceService.delete(carInsuranceID);}

   /* @GetMapping
    public List<CarInsurance> getAllCarInsurances(){return carInsuranceService.getAllCarInsurances();}*/
}
