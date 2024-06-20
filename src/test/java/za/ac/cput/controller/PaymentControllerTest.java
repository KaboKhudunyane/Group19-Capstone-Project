package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.Booking;
import za.ac.cput.domain.CarInformation;
import za.ac.cput.domain.Payment;
import za.ac.cput.factory.BookingFactory;
import za.ac.cput.factory.CarInformationFactory;
import za.ac.cput.factory.PaymentFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PaymentControllerTest {

        @Autowired
        private TestRestTemplate restTemplate;

        private final String BASE_URL = "http://localhost:8080/Group19-Capstone-Project/Payment";

        private static Booking booking;
        private static Payment payment;



        @BeforeAll
        public static void setup() {
             CarInformation carInformation  = CarInformationFactory.buildCarInformation("011", "BMW", "M4",
                    "2017", "CA123-143", "M performance", "Twin turbo");
            booking = BookingFactory.buildBooking("b101","10-June-2024", "15-June-2024",
                    "11 Lowry Street, Cape Town, 8001", "10 Dorset Street, Cape Town, 8001",carInformation,"Blue BMW M4(Manual)"
                    , 25000);

            payment = PaymentFactory.buildPayment("14521", booking, "Capitec", "20-May-2024", 18000, "Declined");
        }

        @Test
        @Order(1)
        void save() {
            String url = BASE_URL + "/save";
            ResponseEntity<Payment> postResponse = restTemplate.postForEntity(url, payment, Payment.class);
            assertNotNull(postResponse);
            assertNotNull(postResponse.getBody());

            Payment paymentSaved = postResponse.getBody();
            assertEquals(payment.getPaymentId(), paymentSaved.getPaymentId());
            System.out.println("Saved data: " + paymentSaved);
        }

        @Test
        @Order(2)
        void read() {
            String url = BASE_URL + "/read/" + payment.getPaymentId();
            System.out.println("URL" + url);
            ResponseEntity<Payment> response = restTemplate.getForEntity(url, Payment.class);
            assertEquals(payment.getPaymentId(), response.getBody().getPaymentId());
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

