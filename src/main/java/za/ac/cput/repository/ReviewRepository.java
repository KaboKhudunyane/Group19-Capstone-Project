package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, String> {
    List<Review> getAllReviews();
    Review findByReviewId(String reviewId);
}

