package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BookingFactoryTest {
    private static final String CAR_PICTURE_PATH ="C:\\Users\\bokam\\OneDrive\\Desktop\\Example.jpeg";

    private byte[] readFileAsBytes(String filePath) {
        try {
            Path path = Paths.get(filePath);
            return Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    //declaring car image
    byte[] carPicture = readFileAsBytes(CAR_PICTURE_PATH);
    Car car = new Car.Builder()
            .setCarInformation(
                    new CarInformation.Builder()
                            .setMake("Toyota")
                            .setModel("Corolla")
                            .setYear("2023")
                            .setLicensePlate("ABC123")
                            .setDescription("New Toyota Corolla")
                            .setFeatures("Bluetooth, Backup Camera, Navigation System")
                            .buildCarInformation())
            .setCarInsurance(
                    new CarInsurance.Builder()
                            .setInsuranceCompany("Insurance Co.")
                            .setPolicyNumber("12345")
                            .setCoverageType("Comprehensive")
                            .setCoverageAmount("100000")
                            .buildCarInsurance())
            .setRentalRate("150")
            .setAvailabilityStatus("Available")
            .setCarPicture(carPicture) // Provide appropriate car picture data here
            .buildCar();

    @Test
    public void testBuildBooking() {
        // Create a Booking using BookingFactory
        Booking booking = BookingFactory.buildBooking(car, "15-June-2024", "20-June-2024",
                "10 Hanover street, Cape Town, 8001", "10 Hanover street, Cape Town, 8001",
                24000);

        assertNotNull(booking);  // Assert that the created Booking object is not null
        assertNotNull(booking.getCar());  // Assert that the booking has a car associated

        System.out.println("Created booking: " + booking);  // Print the created Booking object
    }
}
