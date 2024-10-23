package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import za.ac.cput.domain.CarInformation;
import za.ac.cput.domain.User;
import za.ac.cput.service.CarInformationService;
import za.ac.cput.service.UserService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/carInformation")
@CrossOrigin("http://localhost:5173")
public class CarInformationController {

    @Autowired
    private CarInformationService carInformationService;

    @Autowired
    UserService userService;

    @PostMapping("/create")
    public CarInformation create(@RequestParam("make") String make,
                                 @RequestParam("model") String model,
                                 @RequestParam("year") String year,
                                 @RequestParam("type") String type,
                                 @RequestParam("licensePlate") String licensePlate,
                                 @RequestParam("description") String description,
                                 @RequestParam("features") String features,
                                 @RequestParam("rentalRate") double rentalRate,
                                 @RequestParam("availabilityStatus") String availabilityStatus,
                                 @RequestParam("userId") Long userId,
                                 @RequestParam("picture1") MultipartFile picture1,
                                 @RequestParam("picture2") MultipartFile picture2,
                                 @RequestParam("picture3") MultipartFile picture3) throws IOException {

        // Find the user
        User user = userService.read(userId);

        // Build CarInformation object
        CarInformation carInformation = new CarInformation.Builder()
                .setMake(make)
                .setModel(model)
                .setYear(year)
                .setType(type)
                .setLicensePlate(licensePlate)
                .setDescription(description)
                .setFeatures(features)
                .setRentalRate(rentalRate)
                .setAvailabilityStatus(availabilityStatus)
                .setUser(user)
                .setPicture1(picture1 != null ? picture1.getBytes() : null)
                .setPicture2(picture2 != null ? picture2.getBytes() : null)
                .setPicture3(picture3 != null ? picture3.getBytes() : null)
                .buildCar();

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
