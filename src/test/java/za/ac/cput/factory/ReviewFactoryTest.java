package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.*;
import za.ac.cput.enums.UserRole;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class ReviewFactoryTest {

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
