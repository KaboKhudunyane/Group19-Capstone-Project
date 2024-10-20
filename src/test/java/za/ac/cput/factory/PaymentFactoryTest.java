package za.ac.cput.factory;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.*;
import za.ac.cput.enums.UserRole;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
class PaymentFactoryTest {

    Address address = AddressFactory.createAddress("123 Main St", "Springfield",
            "CityName", "Western Cape", "12345");

    User user = UserFactory.createUser("John", "Doe", "johndoe", "password123", UserRole.USER,
            "123456789", "john@example.com", address,loadPicture("lisence.jpg"), loadPicture("identity.jpg"));
    CarInformation carInformation = CarInformationFactory.buildCarInformation(
            "Toyota", "Scarlet", "2020", "Manual", "Plate-123",
            "Red 5 door car with 50 000km mileage", "Leather seats, Navigation system, Bluetooth", user,
            2000, "Available",
            loadPicture("scarlet1.jpg"),
            loadPicture("scarlet2.jpg"),
            loadPicture("scarlet3.jpg")
    );


    private byte[] loadPicture(String fileName) {
        try {
            Path path = Paths.get("src/images/img-prototype/" + fileName);
            return Files.readAllBytes(path);
        } catch (IOException e) {
            fail("Failed to load picture: " + e.getMessage());
            return null;
        }
    }

    Booking booking = BookingFactory.buildBooking(
            carInformation,
            LocalDate.of(2024, 6, 15),
            LocalDate.of(2024, 6, 20),
            LocalTime.of(10, 0),
            LocalTime.of(10, 0),
            12000, user
    );



    @Test
    void testBuildPayment() {
        Payment payment = new Payment.Builder()
                .setBooking(booking)
                .setPaymentMethod("Credit Card")
                .setPaymentDate("2024-06-15")
                .setAmountCharged(1500.0)
                .setPaymentStatus("Pending")
                .buildPayment();
        assertNotNull(payment);
        assertEquals(booking, payment.getBooking());
        assertEquals("Credit Card", payment.getPaymentMethod());
        assertEquals("2024-06-15", payment.getPaymentDate());
        assertEquals(1500.0, payment.getAmountCharged());
        assertEquals("Pending", payment.getPaymentStatus());
        System.out.println("Created Payment: " + payment);
    }

    @Test
    void testBuildPaymentWithFail() {
        Payment payment = new Payment.Builder()
                .setBooking(booking)
                .setPaymentMethod("Credit Card")
                .setPaymentDate("2024-06-15")
                .setAmountCharged(1500.0)
                .setPaymentStatus(null)
                .buildPayment();
        assertNull(payment);
        System.out.println("Failed Payment: " + payment);
    }
}