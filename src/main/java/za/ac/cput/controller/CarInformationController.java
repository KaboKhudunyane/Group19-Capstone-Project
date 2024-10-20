package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Booking;
import za.ac.cput.domain.CarInformation;
import za.ac.cput.domain.User;
import za.ac.cput.service.CarInformationService;
import za.ac.cput.service.CarInsuranceService;
import za.ac.cput.service.UserService;

import java.util.List;


@RestController
@RequestMapping("/api/carInformation")
@CrossOrigin
public class CarInformationController {

    @Autowired
    private CarInformationService carInformationService;

    @Autowired
    UserService userService;


    @PostMapping("/create")
    public CarInformation create(@RequestBody  CarInformation carInformation){

        userService.read(carInformation.getUser().getUserID());
        return carInformationService.create(carInformation);
    }

    @GetMapping("/getall")
    public List<CarInformation> getAll(){
        return carInformationService.getAll();
    }

    @GetMapping("/findByCity/{city}")
    public List<CarInformation> findCarsByCity(@PathVariable String city) {
        return carInformationService.findCarsByCity(city);
    }

    @GetMapping("/findByCityAndSuburb")
    public List<CarInformation> findCarsByCityAndSuburb(@RequestParam String city, @RequestParam String suburb) {
        return carInformationService.findCarsByCityAndSuburb(city, suburb);
    }

    @GetMapping("/findByLocation")
    public List<CarInformation> findCarsByLocation(@RequestParam String city, @RequestParam String province, @RequestParam String zipCode) {
        return carInformationService.findCarsByLocation(city, province, zipCode);
    }

    @GetMapping("/count")
    public long getCarCount() {
        return carInformationService.countCars();
    }

    @GetMapping("/cars/{userID}")
    public List<CarInformation> getCarsByUserId(@PathVariable Long userID) {
        return carInformationService.getCarsByUser(userID);
    }

    @GetMapping("/check/{licensePlate}")
    public ResponseEntity<Boolean> checkCarExists(@PathVariable String licensePlate) {
        boolean exists = carInformationService.carExistsByLicensePlate(licensePlate);
        return ResponseEntity.ok(exists);
    }
}



