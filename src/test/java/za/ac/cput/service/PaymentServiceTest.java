package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Booking;
import za.ac.cput.domain.CarInformation;
import za.ac.cput.domain.Payment;
import za.ac.cput.factory.PaymentFactory;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public
class PaymentServiceTest {

    @Autowired
    private PaymentService paymentService;


    @Autowired
    private BookingService bookingService;

    private static Long paymentID;
    private Booking booking;
    private Payment payment;

    @BeforeEach
    void setUp() {
        booking = bookingService.read(booking.getBookingID());
        payment = PaymentFactory.buildPayment(booking, "Capitec", "20-May-2024", 18000, "Declined");
    }
    @Test
    @Order(1)
    void create() {
        Payment created = paymentService.create(payment);
        assertNotNull(created);
        paymentID = created.getPaymentID();
        System.out.println(created);
    }

    @Test
    @Order(2)
    void read() {
        Payment read = paymentService.read(paymentID);
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    @Order(4)
    void getAll() {
        System.out.println(paymentService.getAll());
    }
}


