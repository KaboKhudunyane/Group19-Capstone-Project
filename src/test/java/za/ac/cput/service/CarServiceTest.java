package za.ac.cput.service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Car;
import za.ac.cput.domain.CarInformation;
import za.ac.cput.domain.CarInsurance;
import za.ac.cput.factory.CarFactory;
import za.ac.cput.factory.CarInformationFactory;
import za.ac.cput.factory.CarInsuranceFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CarServiceTest {
    @Autowired
    private CarService carService;
    private static final String CAR_PICTURE_PATH = "C:\\Users\\Kabo Khudunyane\\Pictures\\IMG1.PNG";
    private byte[] readFileAsBytes(String filePath) {
        try {
            Path path = Paths.get(filePath);
            return Files.readAllBytes(path);
        } catch (IOException e) {
            System.err.println("Error reading file: " + filePath);
            e.printStackTrace();
            return null;
        }
    }
    private byte[]carPicture = readFileAsBytes(CAR_PICTURE_PATH);
    private static CarInformation carInformation = new CarInformation.Builder()
            .setMake("Toyota")
            .setModel("Corolla")
            .setYear("2020")
            .setLicensePlate("ABC123")
            .setDescription("A reliable sedan")
            .setFeatures("Air conditioning, Power windows")
            .buildCarInformation();
    private static CarInsurance carInsurance = new CarInsurance.Builder()
            .setPolicyNumber("12345")
            .setInsuranceCompany("ABC Insurance")
            .setCoverageType("Comprehensive")
            .setCoverageAmount("R16500")
            .buildCarInsurance();
    Car car = CarFactory.buildCar(2879292L, carInformation, carInsurance, "100", "available", carPicture);
    @Test
    void create() {
        Car createdCar = carService.create(car);
        assertNotNull(createdCar);
        System.out.println("Created Car: " + createdCar);
    }
    @Test
    void read() {
        Car readCar = carService.read(car.getCarID());
        assertNotNull(readCar);
        System.out.println("Read Car: " + readCar);
    }
    @Test
    void update() {
        Car carToUpdate = carService.read(car.getCarID());
        assertNotNull(carToUpdate);
        Car updatedCar = new Car.Builder()
                .copyCar(carToUpdate)
                .setRentalRate("150")
                .buildCar();
        Car savedUpdatedCar = carService.update(updatedCar);
        assertNotNull(savedUpdatedCar);
        assertEquals("150", savedUpdatedCar.getRentalRate());
        System.out.println("Updated Car: " + savedUpdatedCar);
    }
    @Test
    void delete() {
        carService.delete(car.getCarID());
        Car deletedCar = carService.read(car.getCarID());
        assertNull(deletedCar);
        System.out.println("Car deleted successfully");
    }
}
