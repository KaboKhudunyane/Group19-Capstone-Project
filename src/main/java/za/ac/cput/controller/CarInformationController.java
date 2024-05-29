package za.ac.cput.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.CarInformation;
import za.ac.cput.domain.CarMaintenance;
import za.ac.cput.service.CarInformationService;

import java.util.List;

@RestController
    @RequestMapping("/CarInformation")
    public class CarInformationController {
        @Autowired
        private CarInformationService carInformationService;
        @PostMapping("/save")
        public CarInformation save(@RequestBody CarInformation carInformation){
            return carInformationService.create(carInformation);
        }
        @GetMapping("/read/{CarInformationID}")
        public CarInformation read(@PathVariable String carInformationID){
            return carInformationService.read(carInformationID);
        }
        @PutMapping("/update")
        public CarInformation update(@RequestBody CarInformation carInformation) {
            return carInformationService.update(carInformation);
        }
        @DeleteMapping("/delete/{CarInformationID}")
        public void delete (@PathVariable String carInformationID){
            carInformationService.delete(carInformationID);
        }
        @GetMapping("/getAllCarInformation")
        public List<CarInformation> getAllCarInformation(){
            return carInformationService.getAllCarInformation();
        }
}

