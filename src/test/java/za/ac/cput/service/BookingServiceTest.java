package za.ac.cput.service;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Booking;
import za.ac.cput.domain.Car;
import za.ac.cput.domain.CarInformation;
import za.ac.cput.domain.CarInsurance;
import za.ac.cput.factory.BookingFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
class BookingServiceTest {
    @Autowired
    private BookingService bookingService;
    private static final String CAR_PICTURE_PATH = "C:\\Users\\bokam\\OneDrive\\Desktop\\Example.jpeg";
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
    Booking booking = BookingFactory.buildBooking(car, "15-June-2024", "20-June-2024",
            "10 Hanover street, Cape Town, 8001", "10 Hanover street, Cape Town, 8001",
            24000);
    @Test
    void create() {
        Booking saved = bookingService.create(booking);
        assertNotNull(saved);
        System.out.println(saved);
    }
    @Test
    void read (){
        Booking read = bookingService.read(booking.getBookingID());
        assertNotNull(read);
        System.out.println(read);
    }
    @Test
    void update(){
        Booking editedBooking = new Booking.Builder().copy(booking).setReturnDate("11 July 2024").
                buildBooking();
        assertNotNull(editedBooking);
        Booking updated = bookingService.update(editedBooking);
        assertNotNull(updated);
        System.out.println(updated);
    }
    @Test
    void delete(){
        bookingService.delete(booking.getBookingID());
        System.out.println("Success: deleted booking");
    }
    @Test
    void getAll() {
        System.out.println(bookingService.getAll());
    }
}