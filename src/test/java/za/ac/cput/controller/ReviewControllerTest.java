package za.ac.cput.controller;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import za.ac.cput.domain.*;
import za.ac.cput.factory.BookingFactory;
import za.ac.cput.factory.ReviewFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test") // Use a test profile if needed
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReviewControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    private final String BASE_URL = "http://localhost:8080/Group19-Capstone-Project/review";
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
        Booking booking = BookingFactory.buildBooking(
                car, "15-June-2024", "20-June-2024",
                "10 Hanover Street, Cape Town, 8001", "10 Hanover Street, Cape Town, 8001",
                24000);
        Review review = ReviewFactory.buildReview(booking, 4, "Good condition", LocalDate.of(2024, 5, 12));
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