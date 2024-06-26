package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Car;
import za.ac.cput.domain.Review;
import za.ac.cput.factory.ReviewFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReviewServiceTest {

    @Autowired
    private ReviewService reviewService;

    private static Review review1 = ReviewFactory.buildReview("01","12",6d,"Great service", "12 Mayb2024");;
    private static Review review2 = ReviewFactory.buildReview("02","13",4d,"Bad service","20 April 2024");;

    @Test
    @Order(1)
    void Create() {
         Review createdReview = reviewService.create(review1);
        assertNotNull(createdReview);
        System.out.println("Created Review: " + createdReview);
    }
    @Test
    @Order(2)
    void Update() {
        Review newReview = new Review.Builder()
                .copyReview(review1)
                .setReviewId("03")
                .setBookingId("14")
                .buildReview();
        Review updatedReview = reviewService.update(newReview);
        assertNotNull(updatedReview);
        System.out.println("Updated Car: " + updatedReview);
    }
    @Test
    @Order(3)
    void read() {
        Review read = reviewService.read(review2.getReviewId());
        assertNotNull(read);
        System.out.println("Read Review: " + read);
    }

    @Test
    @Order(4)
    void delete() {
        reviewService.delete(review1.getReviewId());
        System.out.println("Car deleted successfully");
    }

   /* @Test
    @Order(5)
    void getAllReviews() {
        List<Review> allReviews = reviewService.getAllReviews();
        assertNotNull(allReviews);
        assertTrue(allReviews.size() > 0);
        System.out.println("All Reviews: " + allReviews);
    }*/
}
