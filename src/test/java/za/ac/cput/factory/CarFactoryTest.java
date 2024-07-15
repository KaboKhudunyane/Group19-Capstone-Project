package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CarFactoryTest {

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
        Car car = CarFactory.buildCar(carInformation, carInsurance, "150", "Available", "New");
        assertNotNull(car);  // Assert that the created Car object is not null
        assertNotNull(car.getCarId());  // Assert that the carId is assigned (assuming it's auto-generated)
        assertNotNull(car.getCarInsurance());  // Assert that the car has carInsurance set
        System.out.println("Created car: " + car);  // Print the created Car object
    }

    @Test
    public void testBuildCarWithNullCarInformation() {
        Car car = CarFactory.buildCar(null, carInsurance, "150", "Available", "New");
        assertNull(car);  // Assert that the factory returns null when CarInformation is null
        System.out.println("Created car with null CarInformation: " + car);  // Print the created Car object (should be null)
    }

    @Test
    public void testBuildCarWithNullCarInsurance() {
        Car car = CarFactory.buildCar(carInformation, null, "150", "Available", "New");
        assertNotNull(car);  // Assert that the created Car object is not null
        assertNull(car.getCarInsurance());  // Assert that the carInsurance is null when not provided
        System.out.println("Created car with null CarInsurance: " + car);  // Print the created Car object
    }

    @Test
    public void testBuildCarWithNullCarInformationAndCarInsurance() {
        Car car = CarFactory.buildCar(null, null, "150", "Available", "New");
        assertNull(car);  // Assert that the factory returns null when both CarInformation and CarInsurance are null
        System.out.println("Created car with null CarInformation and CarInsurance: " + car);  // Print the created Car object (should be null)
    }
}
