package za.ac.cput.service;
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
    private static final String CAR_PICTURE_PATH = "C:/Users/bokam/OneDrive/Desktop/Example.jpeg";

    private byte[] readFileAsBytes(String filePath) {
        try {
            Path path = Paths.get(filePath);
            return Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    byte[] carPicture = readFileAsBytes(CAR_PICTURE_PATH);
     static CarInformation carInformation = new CarInformation.Builder()
            .setMake("Toyota")
            .setModel("Corolla")
            .setYear("2020")
            .setLicensePlate("ABC123")
            .setDescription("A reliable sedan")
            .setFeatures("Air conditioning, Power windows")
            .buildCarInformation();
     static CarInsurance carInsurance = new CarInsurance.Builder()
            .setPolicyNumber("12345")
            .setInsuranceCompany("ABC Insurance")
            .buildCarInsurance();
     Car car = CarFactory.buildCar(carInformation, carInsurance, "100","available","");

    @Test
    void create() {
        Car createdCar = carService.create(car);
        assertNotNull(createdCar);
        System.out.println("Created Car: " + createdCar);
    }

    @Test
    void read() {
        Car readCar = carService.read(car.getCarID() );
        assertNotNull(readCar);
        System.out.println("Read Car: " + readCar);
    }

    @Test
    void update() {
        Car carToUpdate = carService.read(car.getCarID());
        assertNotNull(carToUpdate);

        // Update using the builder pattern
        Car updatedCar = new Car.Builder()
                .copyCar(carToUpdate)
                .setRentalRate("150") // Update rental rate
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
