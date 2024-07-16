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
import za.ac.cput.factory.BookingFactory;
import za.ac.cput.factory.CarInformationFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookingControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8080/Group19-Capstone-Project/booking";
    private static CarInformation carInformation;
    private static Booking booking;

    @BeforeAll
    public static void setup() {
        carInformation = CarInformationFactory.buildCarInformation("011", "BMW", "M4", "2017", "CA 123-456", "It is an M-performance", "800hps, twin turbo Injector");
        booking = BookingFactory.buildBooking("b111", carInformation, "10-June-2024", "15-June-2024",
                "11 Lowry Street, Cape Town, 8001", "10 Dorset Street, Cape Town, 8001", 25000);
    }

    @Test
    @Order(1)
    void save() {
        String url = BASE_URL + "/save";
        ResponseEntity<Booking> postResponse = restTemplate.postForEntity(url, booking, Booking.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());

        Booking savedBooking = postResponse.getBody();
        assertEquals(booking.getBookingId(), savedBooking.getBookingId());
        System.out.println("Saved data: " + savedBooking);
    }

    @Test
    @Order(2)
    void read() {
        String url = BASE_URL + "/read/" + booking.getBookingId();
        System.out.println("URL: " + url);
        ResponseEntity<Booking> response = restTemplate.getForEntity(url, Booking.class);
        assertEquals(booking.getBookingId(), response.getBody().getBookingId());
        System.out.println("Read: " + response.getBody());
    }

    @Test
    void getAllBooking() {
        String url = BASE_URL + "/getAllBooking";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        System.out.println("Show ALL: ");
        System.out.println(response.getBody());
    }
}
