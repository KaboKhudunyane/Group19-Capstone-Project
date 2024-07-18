package za.ac.cput.controller;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.domain.*;
import za.ac.cput.factory.*;
import java.io.IOException;
import java.nio.file.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PaymentControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8080/payment";
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
    Payment payment = PaymentFactory.buildPayment(booking, "Capitec", "20-May-2024", 18000, "Declined");
    @Test
    @Order(1)
    void create() {
        String url = BASE_URL + "/create";
        ResponseEntity<Payment> postResponse = restTemplate.postForEntity(url, payment, Payment.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());

        payment = postResponse.getBody();
        System.out.println("Saved data: " + payment);
    }
    @Test
    @Order(2)
    void read() {
        String url = BASE_URL + "/read/" + payment.getPaymentID();
        System.out.println("URL: " + url);
        ResponseEntity<Payment> response = restTemplate.getForEntity(url, Payment.class);
        assertEquals(payment.getPaymentID(), response.getBody().getPaymentID());
        System.out.println("Read: " + response.getBody());
    }
    @Test
    @Order(3)
    void update() {
        Payment updatedPayment = new Payment.Builder()
                .setBooking(payment.getBooking())
                .setPaymentMethod("Credit Card")
                .setPaymentDate("21-May-2024")
                .setAmountCharged(20000)
                .setPaymentStatus("Approved")
                .buildPayment();

        String url = BASE_URL + "/update";
        HttpEntity<Payment> entity = new HttpEntity<>(updatedPayment, new HttpHeaders());
        ResponseEntity<Payment> response = restTemplate.exchange(url, HttpMethod.PUT, entity, Payment.class);

        assertNotNull(response.getBody());
        assertEquals("Credit Card", response.getBody().getPaymentMethod());
        assertEquals("21-May-2024", response.getBody().getPaymentDate());
        assertEquals(20000, response.getBody().getAmountCharged());
        assertEquals("Approved", response.getBody().getPaymentStatus());
        System.out.println("Updated: " + response.getBody());
    }
    @Test
    @Order(4)
    void delete() {
        String url = BASE_URL + "/delete/" + payment.getPaymentID();
        restTemplate.delete(url);
    }
    @Test
    @Order(5)
    void getAllPayments() {
        String url = BASE_URL + "/getAll";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("Show ALL: ");
        System.out.println(response);
        System.out.println(response.getBody());
    }
}