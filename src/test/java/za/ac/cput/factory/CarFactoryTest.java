package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CarFactoryTest {

    // Example path to a car picture file
    private static final String CAR_PICTURE_PATH = "C:\\Users\\user\\Downloads\\V P I\\Group19-Capstone-Project\\image";

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

    // Read the car picture
    byte[] carPicture = readFileAsBytes(CAR_PICTURE_PATH);

    // Create example CarInformation and CarInsurance objects
    CarInformation carInformation = new CarInformation.Builder()
            .setMake("Toyota")
            .setModel("Corolla")
            .setYear("2023")
            .setLicensePlate("ABC123")
            .setDescription("New Toyota Corolla")
            .setFeatures("Bluetooth, Backup Camera, Navigation System")
            .buildCarInformation();

    CarInsurance carInsurance = new CarInsurance.Builder()
            .setInsuranceCompany("Insurance Co.")
            .setPolicyNumber("12345")
            .setCoverageType("Comprehensive")
            .setCoverageAmount("100000")
            .buildCarInsurance();

    @Test
    public void testBuildCar() {
        Car car = CarFactory.buildCar(23323L ,carInformation, carInsurance, "145", "Available", null);
        assertNotNull(car);
        System.out.println("Created car: " + car);
    }

    @Test
    public void testBuildCarWithFail() {
        Car car = CarFactory.buildCar(28442L, carInformation, carInsurance, "", "Available", carPicture);
        assertNull(car); // Adjust this as needed if you want to test for failure
        System.out.println("Created car: " + car);
    }
}
