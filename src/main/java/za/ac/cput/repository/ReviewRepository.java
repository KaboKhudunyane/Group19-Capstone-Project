package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Review;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {

    Review findByReviewId(String reviewID);

    void deleteByReviewId(String reviewID);

    //List<Review> getAllReviews();
}
