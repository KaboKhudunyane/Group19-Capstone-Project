package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CarFactoryTest {

    // Example path to a car picture file
    private static final String CAR_PICTURE_PATH = "path/to/your/car/picture.jpg";

    // Method to read file content as byte array
    private byte[] readFileAsBytes(String filePath) {
        try {
            Path path = Paths.get(filePath);
            return Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Create a CarInformation object for testing
    CarInformation carInformation = new CarInformation.Builder()
            .setMake("Toyota")
            .setModel("Corolla")
            .setYear("2023")
            .setLicensePlate("ABC123")
            .setDescription("New Toyota Corolla")
            .setFeatures("Bluetooth, Backup Camera, Navigation System")
            .buildCarInformation();

    // Create a CarInsurance object for testing
    CarInsurance carInsurance = new CarInsurance.Builder()
            .setInsuranceCompany("Insurance Co.")
            .setPolicyNumber("12345")
            .setCoverageType("Comprehensive")
            .setCoverageAmount("100000")
            .buildCarInsurance();

    @Test
    public void testBuildCar() {
        // Read car picture file as byte array
        byte[] carPicture = readFileAsBytes(CAR_PICTURE_PATH);
        assertNotNull(carPicture, "Failed to read car picture file");

        // Build Car object using CarFactory
        Car car = CarFactory.buildCar(carInformation, carInsurance, "150", "Available", carPicture);

        assertNotNull(car);  // Assert that the created Car object is not null
        assertNotNull(car.getCarId());  // Assert that the carId is assigned (assuming it's auto-generated)
        assertNotNull(car.getCarInsurance());  // Assert that the car has carInsurance set
        System.out.println("Created car: " + car);  // Print the created Car object
    }
}
