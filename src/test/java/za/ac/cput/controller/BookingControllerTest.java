package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.*;
import za.ac.cput.factory.BookingFactory;
import za.ac.cput.factory.CarInformationFactory;
import za.ac.cput.factory.CarInsuranceFactory;
import za.ac.cput.factory.UserFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookingControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8080/group19-capstone-project/api/booking";

     Name name = new Name.Builder().setFirstName("John").setMiddleName("Fred").setLastName("Doe").buildName();
    Contact contact = new Contact.Builder().setEmail("john@example.com").setMobileNumber("123456789").buildContact();
    Address address = new Address.Builder().setStreetName("123 Main St").setSuburb("Springfield").setCity("Cape Town").setProvince("Western Cape").setZipCode("12345").buildAddress();
    User user = UserFactory.createUser(User.Role.ROLE_USER,"username","password", name, contact, address, loadPicture("lisence.jpg"), loadPicture("identity.jpg"));
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

    Booking booking = BookingFactory.buildBookingTesting(
            carInformation,
            LocalDate.of(2024, 6, 15),
            LocalDate.of(2024, 6, 20),
            LocalTime.of(10, 0),
            LocalTime.of(10, 0),
            12000
    );

    @Test
    @Order(1)
    void create() {
        String url = BASE_URL + "/create";
        ResponseEntity<Booking> postResponse = restTemplate
                .withBasicAuth("user", "password") // Replace with actual credentials
                .postForEntity(url, booking, Booking.class);

        assertNotNull(postResponse, "The response entity should not be null");

        Booking savedBooking = postResponse.getBody();
        assertNotNull(savedBooking, "The response body should not be null");

        assertEquals(booking.getBookingID(), savedBooking.getBookingID());
        System.out.println("Saved data: " + savedBooking);
    }

    @Test
    @Order(2)
    void read() {
        String url = BASE_URL + "/read/" + booking.getBookingID();
        ResponseEntity<Booking> response = restTemplate
                .withBasicAuth("user", "password") // Replace with actual credentials
                .getForEntity(url, Booking.class);

        assertNotNull(response.getBody(), "The response body should not be null");
        assertEquals(booking.getBookingID(), response.getBody().getBookingID());
        System.out.println("Read: " + response.getBody());
    }

    @Test
    @Order(5)
    void getAll() {
        String url = BASE_URL + "/getAll";
        ResponseEntity<List> response = restTemplate
                .withBasicAuth("user", "password") // Replace with actual credentials
                .getForEntity(url, List.class);

        assertNotNull(response.getBody());
        assertTrue(response.getBody().size() > 0, "The booking list should not be empty");
        System.out.println("All Bookings: " + response.getBody());
    }
}
