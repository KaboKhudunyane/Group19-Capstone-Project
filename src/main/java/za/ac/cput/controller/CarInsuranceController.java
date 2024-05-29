package za.ac.cput.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.CarInsurance;
import za.ac.cput.domain.SupportTicket;
import za.ac.cput.service.CarInsuranceService;

import java.util.List;

@RestController
@RequestMapping
public class CarInsuranceController {

    @Autowired
    private CarInsuranceService carInsuranceService;

    @PostMapping
    public CarInsurance create(@RequestBody CarInsurance carInsurance){return carInsuranceService.create(carInsurance);}

    @GetMapping
    public CarInsurance read(@PathVariable String id){return carInsuranceService.read(id);}

    @PostMapping
    public CarInsurance update(@RequestBody CarInsurance carInsurance){return carInsuranceService.update(carInsurance);}

    @DeleteMapping
    public void delete(@PathVariable String id){carInsuranceService.delete(id);}

    @GetMapping
    public List<CarInsurance> getAllCarInsurances(){return carInsuranceService.getAllCarInsurances();}
}
