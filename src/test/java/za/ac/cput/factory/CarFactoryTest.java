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
    byte[] carPicture = readFileAsBytes(CAR_PICTURE_PATH);

    CarInformation carInformation = new CarInformation.Builder().setMake("Toyota").setModel("Corolla").setYear("2023").setLicensePlate("ABC123").setDescription("New Toyota Corolla").setFeatures("Bluetooth, Backup Camera, Navigation System").buildCarInformation();
    CarInsurance carInsurance = new CarInsurance.Builder().setInsuranceCompany("Insurance Co.").setPolicyNumber("12345").setCoverageType("Comprehensive").setCoverageAmount("100000").buildCarInsurance();

    @Test
    public void testBuildCar() {
        Car car = CarFactory.buildCar(carInformation, carInsurance, "150", "Available", carPicture);
        assertNotNull(car);
        assertNotNull(car.getCarID());
        assertNotNull(car.getCarInsurance());
        System.out.println("Created car: " + car);
    }
    @Test
    public void testBuildCarWithFail() {
        Car car = CarFactory.buildCar(carInformation, carInsurance, "", "Available", carPicture);
        assertNotNull(car);
        assertNotNull(car.getCarID());
        assertNotNull(car.getCarInsurance());
        System.out.println("Created car: " + car);
    }
}