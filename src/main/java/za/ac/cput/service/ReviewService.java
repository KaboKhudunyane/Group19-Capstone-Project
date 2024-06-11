package za.ac.cput.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Review;
import za.ac.cput.domain.User;
import za.ac.cput.repository.ReviewRepository;

import java.util.List;
@Service
public class ReviewService implements IService<Review, String>{
    private ReviewRepository reviewRepository;
    @Autowired
    ReviewService(ReviewRepository repository) {
        this.reviewRepository = repository;
    }
    public Review save(Review review) {
        return reviewRepository.save(review);
    }
    @Override
    public Review create(Review review) {
        return reviewRepository.save(review);
    }
    @Override
    public Review read(String reviewId) {
        return reviewRepository.findByReviewID(reviewId);
    }
    @Override
    public Review update(Review review) {
        return reviewRepository.save(review);
    }
    public void delete(String reviewId) {
        reviewRepository.deleteByReviewID(reviewId);
        return false;
    }
    public List<Review> getAllReviews() {
        return reviewRepository.getAllReviews();
    }
}
