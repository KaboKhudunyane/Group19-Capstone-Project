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

    @Autowired
    private CarService carService;

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
                            .setMake("BMW")
                            .setModel("3 Series")
                            .setYear("2024")
                            .setLicensePlate("CA123")
                            .setDescription("New 3 Series Coupe")
                            .setFeatures("Bluetooth, Navigation System")
                            .buildCarInformation())
            .setCarInsurance(
                    new CarInsurance.Builder()
                            .setInsuranceCompany("Insurance Co.")
                            .setPolicyNumber("65896")
                            .setCoverageType("Business")
                            .setCoverageAmount("200000")
                            .buildCarInsurance())
            .setRentalRate("500")
            .setAvailabilityStatus("Available")
            .setCarPicture(carPicture)
            .buildCar();
    private Booking booking;
    private Payment payment;

    @BeforeEach
    void setUp() {
        car = carService.create(car);
        assertNotNull(car, "Car should be saved and not null");
        booking = BookingFactory.buildBooking(car, "15-June-2024", "20-June-2024",
                "10 Hanover street, Cape Town, 8001", "10 Hanover street, Cape Town, 8001",
                24000);
        booking = bookingService.create(booking);
        assertNotNull(booking, "Booking should be saved and not null");

        payment = PaymentFactory.buildPayment(booking, "Capitec", "20-May-2024", 18000, "Declined");
        payment = paymentService.create(payment); // Persist the payment
        assertNotNull(payment, "Payment should be saved and not null");
    }

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
