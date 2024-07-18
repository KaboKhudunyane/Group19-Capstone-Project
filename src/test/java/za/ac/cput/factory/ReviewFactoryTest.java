package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ReviewFactoryTest {
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
            .setCarPicture(carPicture) // Provide appropriate car picture data here
            .buildCar();

    Booking booking = BookingFactory.buildBooking(car, "15-June-2024", "20-June-2024",
            "10 Hanover Street, Cape Town, 8001", "10 Hanover Street, Cape Town, 8001",
            24000);

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
