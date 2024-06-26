package za.ac.cput.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.CarInformation;
import za.ac.cput.service.CarInformationService;

@RestController
    @RequestMapping("/CarInformation")
    public class CarInformationController {
        @Autowired
        private CarInformationService carInformationService;
        @PostMapping("/save")
        public CarInformation save(@RequestBody CarInformation carInformation){
            return carInformationService.create(carInformation);
        }
        @GetMapping("/read/{carInformationId}")
        public CarInformation read(@PathVariable String carInformationId){
            return carInformationService.read(carInformationId);
        }
        @PutMapping("/update")
        public CarInformation update(@RequestBody CarInformation carInformation) {
            return carInformationService.update(carInformation);
        }
        @DeleteMapping("/delete/{carInformationId}")
        public void delete (@PathVariable String carInformationId){
            carInformationService.delete(carInformationId);
        }
       /* @GetMapping("/getAllCarInformation")
        public List<CarInformation> getAllCarInformation(){
            return carInformationService.getAllCarInformation();
        }*/
}

