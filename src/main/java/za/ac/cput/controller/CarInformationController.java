package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.CarInformation;
import za.ac.cput.domain.User;
import za.ac.cput.factory.CarInformationFactory;
import za.ac.cput.service.CarInformationService;
import za.ac.cput.service.UserService;
import java.util.Base64;
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
    public ResponseEntity<?> addCarInformation(@RequestBody CarInformation carInformation) {
        try {
            User user = userService.read(carInformation.getUser().getUserID());
            // Validate user
            if (user == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found.");
            }

            // Decode base64 pictures
            byte[] picture1 = carInformation.getPicture1Base64() != null ? Base64.getDecoder().decode(carInformation.getPicture1Base64()) : null;
            byte[] picture2 = carInformation.getPicture2Base64() != null ? Base64.getDecoder().decode(carInformation.getPicture2Base64()) : null;
            byte[] picture3 = carInformation.getPicture3Base64() != null ? Base64.getDecoder().decode(carInformation.getPicture3Base64()) : null;

            // Build the CarInformation object
            CarInformation createCarInformation = CarInformationFactory.buildCarInformation(
                    carInformation.getMake(), carInformation.getModel(),
                    carInformation.getYear(), carInformation.getType(),
                    carInformation.getLicensePlate(), carInformation.getDescription(),
                    carInformation.getFeatures(), user, carInformation.getRentalRate(),
                    carInformation.getAvailabilityStatus(), picture1, picture2, picture3
            );

            // Save the car information
            CarInformation savedCarInformation = carInformationService.create(createCarInformation);

            // Return the created CarInformation object, including the carInformationID
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCarInformation);
        } catch (Exception e) {
            // Log the error and return an appropriate response
            e.printStackTrace(); // Log the exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }

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
