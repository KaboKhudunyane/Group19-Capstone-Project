package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Booking;
import za.ac.cput.domain.Payment;
import za.ac.cput.domain.Review;
import za.ac.cput.factory.ReviewFactory;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ReviewServiceTest {

    @Autowired
    private ReviewService reviewService;


    @Autowired
    private BookingService bookingService;

    private static Long reviewID;
    private Booking booking;
    private Review review;
    @BeforeEach
    void setUp() {
        booking = bookingService.read(booking.getBookingID());
        review = ReviewFactory.buildReview(booking, 5, "Excellent service!", LocalDate.of(2024, 6, 21));

    }
    @Test
    @Order(1)
    void create() {
        Review created = reviewService.create(review);
        assertNotNull(created);
        reviewID = created.getReviewID();
        System.out.println(created);
    }

    @Test
    @Order(2)
    void read() {
        Review read = reviewService.read(reviewID);
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    @Order(4)
    void getAll() {
        System.out.println(reviewService.getAll());
    }

}
