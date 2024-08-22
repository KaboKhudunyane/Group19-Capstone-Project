package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class ReviewFactoryTest {

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
    public void buildReview() {
        Review review = ReviewFactory.buildReview(booking, 5, "Excellent service!", LocalDate.of(2024, 6, 21));
        assertNotNull(review);
        assertEquals(booking, review.getBooking());
        assertEquals(5, review.getRating());
        assertEquals("Excellent service!", review.getComment());
        assertEquals(LocalDate.of(2024, 6, 21), review.getReviewDate());
    }

    @Test
    void testBuildReviewWithFail() {
        Review reviewWithNullBooking = ReviewFactory.buildReview(null, 5, "Great service!", LocalDate.of(2024, 6, 21));
        assertNull(reviewWithNullBooking);

        Review reviewWithZeroRating = ReviewFactory.buildReview(booking, 0, "Great service!", LocalDate.of(2024, 6, 21));
        assertNull(reviewWithZeroRating);

        Review reviewWithEmptyComment = ReviewFactory.buildReview(booking, 5, "", LocalDate.of(2024, 6, 21));
        assertNull(reviewWithEmptyComment);

        Review reviewWithNullDate = ReviewFactory.buildReview(booking, 5, "Great service!", null);
        assertNull(reviewWithNullDate);
    }
}
