package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Review;

import static org.junit.jupiter.api.Assertions.*;

class ReviewFactoryTest {

    @Test
    void buildReview() {
        Review review = ReviewFactory.buildReview("123", 20.99d, "Good condition", "12 May 2024");
        assertNotNull(review);
        System.out.println(review);
    }

    @Test
    void testBuildReviewWithFail() {
        Review review = ReviewFactory.buildReview("", 20.22d, "moderate", "11 May 2024");
        assertNotNull(review);
        System.out.println(review);
    }
}