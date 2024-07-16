package za.ac.cput.factory;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;
class PaymentFactoryTest {
    private static final String CAR_PICTURE_PATH = "path/to/your/car/picture.jpg";

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
            .setCarPicture(carPicture)
            .buildCar();
    Booking booking = BookingFactory.buildBooking("b101", car, "15-June-2024", "20-June-2024",
            "10 Hanover street, Cape Town, 8001", "10 Hanover street, Cape Town, 8001",
            24000);

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
                .setPaymentStatus("Pending")
                .buildPayment();
        assertNull(payment);
        System.out.println("Failed Payment: " + payment);
    }
}