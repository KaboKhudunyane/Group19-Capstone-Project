package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class BookingFactoryTest {

    private byte[] loadPicture(String filePath) {
        try {
            Path path = Paths.get(filePath);
            return Files.readAllBytes(path);
        } catch (IOException e) {
            fail("Failed to load picture: " + e.getMessage());
            return null;
        }
    }
    CarInsurance carInsurance = CarInsuranceFactory.buildCarInsurance(
            "MiWay", 15447841, "Insurance", 1200
    );
    CarInformation carInformation = CarInformationFactory.buildCarInformation(
            "Toyota", "Scarlet", "2020", "Manual", "Plate-123",
            "A stylish and comfortable SUV.", "Leather seats, Navigation system, Bluetooth", carInsurance,
            200, "Available",
            loadPicture("C:\\Users\\Lehlogonolo Mahlangu\\Downloads\\scarlet1.jpg"), // Load the first picture
            loadPicture("C:\\Users\\Lehlogonolo Mahlangu\\Downloads\\scarlet2.jpg"), // Load the second picture
            loadPicture("C:\\Users\\Lehlogonolo Mahlangu\\Downloads\\scarlet3.jpg")  // Load the third picture
    );

    Booking booking = BookingFactory.buildBooking(
    carInformation,
            LocalDate.of(2024, 6, 15),
            LocalDate.of(2024, 6, 20),
            LocalTime.of(10, 0),
            LocalTime.of(10, 0),
            12000
            );

    @Test
    public void testBuildBooking() {
        assertNotNull(booking);  // Assert that the created Booking object is not null
        assertNotNull(booking.getCar());  // Assert that the booking has a car associated

        System.out.println("Created booking: " + booking);  // Print the created Booking object
    }
}
