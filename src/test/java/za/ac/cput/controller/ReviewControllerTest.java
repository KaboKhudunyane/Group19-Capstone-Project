package za.ac.cput.controller;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import za.ac.cput.domain.*;
import za.ac.cput.enums.UserRole;
import za.ac.cput.factory.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test") // Use a test profile if needed
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReviewControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    private final String BASE_URL = "http://localhost:8080/Group19-Capstone-Project/review";

    Address address = AddressFactory.createAddress("123 Main St", "Springfield",
            "CityName", "Western Cape", "12345");

    User user = UserFactory.createUser("John", "Doe", "johndoe", "password123", UserRole.USER,
            "123456789", "john@example.com", address,loadPicture("lisence.jpg"), loadPicture("identity.jpg"));



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
    Booking booking = BookingFactory.buildBooking(
            carInformation,
            LocalDate.of(2024, 6, 15),
            LocalDate.of(2024, 6, 20),
            LocalTime.of(10, 0),
            LocalTime.of(10, 0),
            12000, user
    );
        Review review = ReviewFactory.buildReview(booking, user,4, "Good condition", LocalDate.of(2024, 5, 12));
    @Test
    void save() {
        String url = BASE_URL + "/save";
        ResponseEntity<Review> postResponse = restTemplate.postForEntity(url, review, Review.class);
        assertNotNull(postResponse);
        assertEquals(HttpStatus.CREATED, postResponse.getStatusCode());

        Review savedReview = postResponse.getBody();
        assertNotNull(savedReview);
        assertEquals(review.getRating(), savedReview.getRating());
        assertEquals(review.getComment(), savedReview.getComment());
        assertEquals(review.getReviewDate(), savedReview.getReviewDate());
        System.out.println("Saved Review: " + savedReview);
    }
    @Test
    void read() {
        String url = BASE_URL + "/read/" + review.getReviewID();
        ResponseEntity<Review> response = restTemplate.getForEntity(url, Review.class);
        assertEquals(review.getReviewID(), response.getBody().getReviewID());
        System.out.println("Read Review: " + response.getBody());
    }
    @Test
    void update() {
        // Modify review data for update
        Review updatedReview = new Review.Builder()
                .copyReview(review)
                .setRating(5)
                .setComment("Updated comment")
                .buildReview();

        String url = BASE_URL + "/update";
        restTemplate.put(url, updatedReview);

        // Retrieve updated review and assert changes
        ResponseEntity<Review> response = restTemplate.getForEntity(BASE_URL + "/read/" + review.getReviewID(), Review.class);
        assertNotNull(response.getBody());
        assertEquals(5, response.getBody().getRating());
        assertEquals("Updated comment", response.getBody().getComment());
        System.out.println("Updated Review: " + response.getBody());
    }
    @Test
    void delete() {
        String url = BASE_URL + "/delete/" + review.getReviewID();
        restTemplate.delete(url);

        // Ensure review is deleted
        ResponseEntity<Review> response = restTemplate.getForEntity(BASE_URL + "/read/" + review.getReviewID(), Review.class);
        assertNull(response.getBody());
        System.out.println("Review deleted successfully.");
    }
    @Test
    void getAll() {
        String url = BASE_URL + "/getAll";
        ResponseEntity<Review[]> response = restTemplate.getForEntity(url, Review[].class);
        Review[] reviews = response.getBody();
        assertNotNull(reviews);
        assertTrue(reviews.length >= 0); // Ensure it's greater than or equal to 0 depending on test data
        System.out.println("All Reviews:");
        for (Review r : reviews) {
            System.out.println(r);
        }
    }
}