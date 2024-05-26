package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Car;
import za.ac.cput.domain.CarInformation;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CarFactoryTest {
    // Create a CarInformation object for testing
    CarInformation carInformation = new CarInformation.Builder()
            .setCarInformationId("1")
            .setMake("Toyota")
            .setModel("Corolla")
            .setYear("2023")
            .setLicensePlate("ABC123")
            .setDescription("New Toyota Corolla")
            .setFeatures("Bluetooth, Backup Camera, Navigation System")
            .setCarId("1")
            .build();

    @Test
    public void testBuildCar(){
        // Create a car with valid parameters
        Car car = CarFactory.createCar("123", "456",
                carInformation, "150",
                "Available", "New");
        assertNotNull(car);  // Assert that the created Car object is not null
        System.out.println(car);  // Print the created Car object
    }

    @Test
    public void testBuildCarWithFail(){
        // Create a car with all null parameters so the factory returns null
        Car car = CarFactory.createCar(null, null, null, null, null, null);
        assertNotNull(car);  // This will fail because the factory should return null
        System.out.println(car);  // Print the created Car object (should be null)
    }
}
