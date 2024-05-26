package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.CarInformation;
import za.ac.cput.service.CarInformationService;

import java.util.List;


@RestController
    @RequestMapping("/CarInformation")
    public class CarInformationController {

        @Autowired
        private CarInformationService carInformationService;

        @PostMapping("/save")
        public CarInformation save(@RequestBody CarInformation carInformation){
            return carInformationService.save(carInformation);
        }

        @GetMapping("/read/{CarInformationId}")
        public CarInformation read(@PathVariable String CarInformationId){
            return carInformationService.read(CarInformationId);
        }

        @DeleteMapping("/delete/{CarInformationId}")
        public void delete (@PathVariable String CarInformationId){
           carInformationService.delete(CarInformationId);}


        @GetMapping("/getall")
        public List<CarInformation> getAll(){
            return carInformationService.getCarInformationAll();
        }
    }

