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
import za.ac.cput.domain.Car;
import za.ac.cput.domain.CarInformation;
import za.ac.cput.domain.CarInsurance;
import za.ac.cput.factory.BookingFactory;
import za.ac.cput.factory.CarInformationFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookingControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    private final String BASE_URL = "http://localhost:8080/Group19-Capstone-Project/booking";
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

    Booking booking = BookingFactory.buildBooking(car, "15-June-2024", "20-June-2024",
            "10 Hanover street, Cape Town, 8001", "10 Hanover street, Cape Town, 8001",
            24000);

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
    void update() {
        Booking updatedBooking = new Booking.Builder().copy(booking).setPickUpLocation("25-June-2024").buildBooking();

        String url = BASE_URL + "/update";
        HttpEntity<Booking> requestEntity = new HttpEntity<>(booking, new HttpHeaders());
        ResponseEntity<Booking> response = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Booking.class);
        assertEquals("25-June-2024", response.getBody().getPickUpLocation());
        System.out.println("Updated booking: " + response.getBody());
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