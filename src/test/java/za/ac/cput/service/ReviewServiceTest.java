package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Review;
import za.ac.cput.factory.ReviewFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReviewServiceTest {

    @Autowired
    private ReviewService reviewService;

    private static Review review1;
    private static Review review2;

    @BeforeEach
    void setUp() {
        review1 = ReviewFactory.buildReview("01","12",6d,"Great service", "12 Mayb2024");
        assertNotNull(review1);
        review2 = ReviewFactory.buildReview("02","13",4d,"Bad service","20 April 2024");
        assertNotNull(review2);
    }

    @Test
    @Order(1)
    void save() {
        Review savedReview1 = reviewService.save(review1);
        assertNotNull(savedReview1);
        System.out.println("Saved Review 1: " + savedReview1);
        Review savedReview2 = reviewService.save(review2);
        assertNotNull(savedReview2);
        System.out.println("Saved Review 2: " + savedReview2);
    }

    @Test
    @Order(2)
    void read() {
        Review read = reviewService.read(review2.getReviewId());
        assertNotNull(read);
        System.out.println("Read Review: " + read);
    }

    @Test
    @Order(3)
    void delete() {
        boolean deleted = reviewService.delete(review1.getReviewId());
        assertTrue(deleted);
        System.out.println("Deleted Review 1");
    }

    @Test
    @Order(4)
    void getAll() {
        List<Review> allReviews = reviewService.getAll();
        assertNotNull(allReviews);
        assertTrue(allReviews.size() > 0);
        System.out.println("All Reviews: " + allReviews);
    }
}
