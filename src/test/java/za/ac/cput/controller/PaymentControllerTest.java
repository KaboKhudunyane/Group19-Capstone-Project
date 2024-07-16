package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.*;
import za.ac.cput.factory.BookingFactory;
import za.ac.cput.factory.CarInformationFactory;
import za.ac.cput.factory.PaymentFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PaymentControllerTest {

        @Autowired
        private TestRestTemplate restTemplate;
        private final String BASE_URL = "http://localhost:8080/Group19-Capstone-Project/Payment";
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
            .setCarPicture(carPicture) // Provide appropriate car picture data here
            .buildCar();
    Booking booking = BookingFactory.buildBooking("b101", car, "15-June-2024", "20-June-2024",
            "10 Hanover street, Cape Town, 8001", "10 Hanover street, Cape Town, 8001",
            24000);
    Payment payment = PaymentFactory.buildPayment(booking,"Capitec", "20-May-2024", 18000,"Declined");
    @Test
        @Order(1)
        void save() {
            String url = BASE_URL + "/save";
            ResponseEntity<Payment> postResponse = restTemplate.postForEntity(url, payment, Payment.class);
            assertNotNull(postResponse);
            assertNotNull(postResponse.getBody());

            Payment paymentSaved = postResponse.getBody();
            assertEquals(payment.getPaymentID(), paymentSaved.getPaymentID());
            System.out.println("Saved data: " + paymentSaved);
        }
        @Test
        @Order(2)
        void read() {
            String url = BASE_URL + "/read/" + payment.getPaymentID();
            System.out.println("URL" + url);
            ResponseEntity<Payment> response = restTemplate.getForEntity(url, Payment.class);
            assertEquals(payment.getPaymentID(), response.getBody().getPaymentID());
            System.out.println("Read: " + response.getBody());
        }
        @Test
        void getAllPayments() {
            String url = BASE_URL + "/getAllPayments";
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> entity = new HttpEntity<>(null, headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            System.out.println("Show ALL: ");
            System.out.println(response);
            System.out.println(response.getBody());
        }
    }