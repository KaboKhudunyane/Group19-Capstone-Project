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
        @PostMapping("/create")
        public CarInformation create(@RequestBody CarInformation carInformation){
            return carInformationService.create(carInformation);
        }
        @GetMapping("/read/{make}")
        public CarInformation read(@PathVariable String make){
            return carInformationService.read(make);
        }
        @PutMapping("/update")
        public CarInformation update(@RequestBody CarInformation carInformation) {
            return carInformationService.update(carInformation);
        }
        @DeleteMapping("/delete/{make}")
        public void delete (@PathVariable String make){
            carInformationService.delete(make);
        }
        @GetMapping("/getAll")
        public List<CarInformation> getAll(){
            return carInformationService.getAll();
        }
}