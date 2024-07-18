package za.ac.cput.service;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.*;
import za.ac.cput.factory.*;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PaymentServiceTest {
    @Autowired
    private PaymentService paymentService;
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
            .setCarPicture("C:/Users/bokam/OneDrive/Desktop/Example.jpeg")
            .buildCar();
    Booking booking = BookingFactory.buildBooking(car, "15-June-2024", "20-June-2024",
            "10 Hanover street, Cape Town, 8001", "10 Hanover street, Cape Town, 8001",
            24000);
    private Payment payment = PaymentFactory.buildPayment(booking, "Capitec", "20-May-2024", 18000, "Declined");
    @Test
    void create() {
        Payment created = paymentService.create(payment);
        assertNotNull(created);
        assertEquals(payment.getPaymentMethod(), created.getPaymentMethod());
        System.out.println("Created Payment: " + created);
    }
    @Test
    void read() {
        Payment read = paymentService.read(payment.getPaymentID());
        assertNotNull(read);
        System.out.println("Read Payment: " + read);
    }
    @Test
    void update() {
        Payment updated = new Payment.Builder()
                .copyPayment(payment)
                .setPaymentStatus("Approved")
                .buildPayment();
        Payment result = paymentService.update(updated);
        assertNotNull(result);
        assertEquals("Approved", result.getPaymentStatus());
        System.out.println("Updated Payment: " + result);
    }
    @Test
    void delete() {
        paymentService.delete(payment.getPaymentID());
        Payment deleted = paymentService.read(payment.getPaymentID());
        assertNull(deleted);
        System.out.println("Deleted Payment: " + deleted);
    }
    @Test
    void getAllPayments() {
        List<Payment> payments = paymentService.getAll();
        assertNotNull(payments);
        assertTrue(payments.size() > 0);
        System.out.println("All Payments: " + payments);
    }
}