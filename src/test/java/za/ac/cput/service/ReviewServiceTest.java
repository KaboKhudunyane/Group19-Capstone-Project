package za.ac.cput.service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.*;
import za.ac.cput.factory.BookingFactory;
import za.ac.cput.factory.ReviewFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ReviewServiceTest {
    @Autowired
    private ReviewService reviewService;
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
            .setCarPicture("C:/Users/bokam/OneDrive/Desktop/Example.jpeg") // Provide appropriate car picture data here
            .buildCar();
    Booking booking = BookingFactory.buildBooking(car, "15-June-2024", "20-June-2024",
            "10 Hanover street, Cape Town, 8001", "10 Hanover street, Cape Town, 8001",
            24000);
    Review review = ReviewFactory.buildReview(booking, 5, "Excellent service!", LocalDate.of(2024, 6, 21));

    @Test
    void createReview() {

        Review createdReview = reviewService.create(review);
        assertNotNull(createdReview);
        assertEquals(5, createdReview.getRating());
        assertEquals("Excellent service!", createdReview.getComment());
        assertEquals(LocalDate.of(2024, 6, 21), createdReview.getReviewDate());
        System.out.println("Created Review: " + createdReview);
    }
    @Test
    void readReview() {
        Review review = new Review.Builder()
                .setBooking(booking)
                .setRating(4)
                .setComment("Good condition")
                .setReviewDate(LocalDate.of(2024, 5, 12))
                .buildReview();

        Review createdReview = reviewService.create(review);

        assertNotNull(createdReview);

        Review foundReview = reviewService.read(createdReview.getReviewID());

        assertNotNull(foundReview);
        assertEquals(review.getReviewID(), foundReview.getReviewID());

        System.out.println("Read Review: " + foundReview);
    }

    @Test
    void updateReview() {
        Review review = new Review.Builder()
                .setBooking(booking)
                .setRating(4)
                .setComment("Good condition")
                .setReviewDate(LocalDate.of(2024, 5, 12))
                .buildReview();

        Review createdReview = reviewService.create(review);

        assertNotNull(createdReview);

        Review updatedReview = new Review.Builder()
                .copyReview(createdReview)
                .setRating(5)
                .setComment("Excellent service")
                .setReviewDate(LocalDate.of(2024, 6, 1))
                .buildReview();

        Review savedReview = reviewService.update(updatedReview);

        assertNotNull(savedReview);
        assertEquals(5, savedReview.getRating());
        assertEquals("Excellent service", savedReview.getComment());
        assertEquals(LocalDate.of(2024, 6, 1), savedReview.getReviewDate());

        System.out.println("Updated Review: " + savedReview);
    }

    @Test
    void deleteReview() {
        Review review = new Review.Builder()
                .setBooking(booking)
                .setRating(4)
                .setComment("Good condition")
                .setReviewDate(LocalDate.of(2024, 5, 12))
                .buildReview();

        Review createdReview = reviewService.create(review);

        assertNotNull(createdReview);

        reviewService.delete(createdReview.getReviewID());

        Review deletedReview = reviewService.read(createdReview.getReviewID());

        assertNull(deletedReview);

        System.out.println("Review deleted successfully.");
    }

    @Test
    void getAllReviews() {
        Review review1 = new Review.Builder()
                .setBooking(booking)
                .setRating(4)
                .setComment("Good condition")
                .setReviewDate(LocalDate.of(2024, 5, 12))
                .buildReview();

        Review review2 = new Review.Builder()
                .setBooking(booking)
                .setRating(5)
                .setComment("Excellent condition")
                .setReviewDate(LocalDate.of(2024, 6, 15))
                .buildReview();

        reviewService.create(review1);
        reviewService.create(review2);

        List<Review> reviews = reviewService.getAll();

        assertNotNull(reviews);
        assertTrue(reviews.size() >= 2);

        System.out.println("All Reviews: " + reviews);
    }
}
