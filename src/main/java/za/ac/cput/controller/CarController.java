package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import za.ac.cput.domain.*;
import za.ac.cput.service.CarService;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    @ControllerAdvice
    public class GlobalExceptionHandler {
        @ExceptionHandler(Exception.class)
        public ResponseEntity<String> handleAllExceptions(Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Car> create(@RequestParam("make") String make,
                                      @RequestParam("model") String model,
                                      @RequestParam("year") String year,
                                      @RequestParam("licensePlate") String licensePlate,
                                      @RequestParam("description") String description,
                                      @RequestParam("features") String features,
                                      @RequestParam("policyNumber") String policyNumber,
                                      @RequestParam("insuranceCompany") String insuranceCompany,
                                      @RequestParam("coverageType") String coverageType,
                                      @RequestParam("coverageAmount") String coverageAmount,
                                      @RequestParam("rentalRate") String rentalRate,
                                      @RequestParam("status") String status,
                                      @RequestParam(value = "carPicture", required = false) MultipartFile carPictureFile)
            throws IOException {
        // Process the file into a byte array
        byte[] carPictureBytes = carPictureFile != null ? carPictureFile.getBytes() : null;

        // Create the CarInformation and CarInsurance objects
        CarInformation carInformation = new CarInformation.Builder()
                .setMake(make)
                .setModel(model)
                .setYear(year)
                .setLicensePlate(licensePlate)
                .setDescription(description)
                .setFeatures(features)
                .buildCarInformation();

        CarInsurance carInsurance = new CarInsurance.Builder()
                .setPolicyNumber(policyNumber)
                .setInsuranceCompany(insuranceCompany)
                .setCoverageType(coverageType)
                .setCoverageAmount(coverageAmount)
                .buildCarInsurance();

        // Create the Car object with the processed image
        Car car = new Car.Builder()
                .setCarInformation(carInformation)
                .setCarInsurance(carInsurance)
                .setRentalRate(rentalRate)
                .setAvailabilityStatus(status)
                .setCarPicture(carPictureBytes)
                .buildCar();

        // Create the car using the service
        Car createdCar = carService.create(car);
        return ResponseEntity.ok(createdCar);
    }

    @GetMapping("/read/{carID}")
    public ResponseEntity<Car> read(@PathVariable Long carID) {
        Car car = carService.read(carID);
        return car != null ? ResponseEntity.ok(car) : ResponseEntity.notFound().build();
    }
    @PutMapping("/update")
    public ResponseEntity<Car> update(@RequestBody Car car) {
        Car updatedCar = carService.update(car);
        return ResponseEntity.ok(updatedCar);
    }
    @DeleteMapping("/delete/{carID}")
    public ResponseEntity<Void> delete(@PathVariable Long carID) {
        carService.delete(carID);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Car>> getAll() {
        List<Car> cars = carService.getAll();
        return ResponseEntity.ok(cars);
    }
}
