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
import za.ac.cput.domain.CarInsurance;
import za.ac.cput.factory.BookingFactory;
import za.ac.cput.factory.CarInformationFactory;
import za.ac.cput.factory.CarInsuranceFactory;

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
    private final String BASE_URL = "http://localhost:8080/group19-capstone-project/booking";

    private byte[] loadPicture(String filePath) {
        try {
            Path path = Paths.get(filePath);
            return Files.readAllBytes(path);
        } catch (IOException e) {
            fail("Failed to load picture: " + e.getMessage());
            return null;
        }
    }
    CarInsurance carInsurance = CarInsuranceFactory.buildCarInsurance(
            "MiWay", 15447841, "Insurance", 1200
    );
    CarInformation carInformation = CarInformationFactory.buildCarInformation(
            "Toyota", "Scarlet", "2020", "Manual", "Plate-123",
            "A stylish and comfortable SUV.", "Leather seats, Navigation system, Bluetooth", carInsurance,
            200, "Available",
            loadPicture("C:\\Users\\Lehlogonolo Mahlangu\\Downloads\\scarlet1.jpg"), // Load the first picture
            loadPicture("C:\\Users\\Lehlogonolo Mahlangu\\Downloads\\scarlet2.jpg"), // Load the second picture
            loadPicture("C:\\Users\\Lehlogonolo Mahlangu\\Downloads\\scarlet3.jpg")  // Load the third picture
    );

    Booking booking = BookingFactory.buildBooking(
            carInformation,
            LocalDate.of(2024, 6, 15),
            LocalDate.of(2024, 6, 20),
            LocalTime.of(10, 0),
            LocalTime.of(10, 0),
            12000
    );


    @Test
    @Order(1)
    void save() {
        String url = BASE_URL + "/create";
        ResponseEntity<Booking> postResponse = restTemplate.postForEntity(url, booking, Booking.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());

        Booking savedBooking = postResponse.getBody();
        assertEquals(booking.getBookingID(), savedBooking.getBookingID());
        System.out.println("Saved data: " + savedBooking);
    }

    @Test
    void read() {
        String url = BASE_URL + "/read/" + booking.getBookingID();
        ResponseEntity<Booking> response = restTemplate.getForEntity(url, Booking.class);
        assertEquals(booking.getBookingID(), response.getBody().getBookingID());
        System.out.println("Read: " + response.getBody());
    }



    @Test
    void delete() {
        String url = BASE_URL + "/delete/" + booking.getBookingID();
        restTemplate.delete(url);
        ResponseEntity<Booking> response = restTemplate.getForEntity(BASE_URL + "/read/" + booking.getBookingID(), Booking.class);
        assertEquals(404, response.getStatusCodeValue());
        System.out.println("Booking deleted successfully.");
    }

    @Test
    @Order(5)
    void getAll() {
        String url = BASE_URL + "/getAll";
        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
        assertNotNull(response.getBody());
        System.out.println("All Bookings: " + response.getBody());
    }
}