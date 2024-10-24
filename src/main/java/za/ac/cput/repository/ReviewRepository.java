package za.ac.cput.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Review;

import java.util.List;
@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Review findByReviewID(Long reviewID);
    void deleteByReviewID(Long reviewID);

    List<Review> findReviewByUserUserID(Long userID);
}