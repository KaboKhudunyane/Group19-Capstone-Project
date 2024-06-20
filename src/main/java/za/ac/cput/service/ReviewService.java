package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Review;
import za.ac.cput.repository.ReviewRepository;

import java.util.List;
@Service
public class ReviewService implements IService<Review, String>{
    private ReviewRepository repository;

    @Autowired
    ReviewService(ReviewRepository repository) {
        this.repository = repository;
    }


    @Override
    public Review read(String reviewId) {
        return repository.findByReviewID(reviewId);
    }

    public void delete(String reviewId) {
        Review review = repository.findByReviewID(reviewId);
        if (review != null) {
            repository.delete(review);
        }
    }

    @Override
    public Review create(Review review) {
        return repository.save(review);
    }

    @Override
    public Review update(Review review) {
        return repository.save(review);
    }

    public List<Review> getAllReviews() {
        return repository.findAll();
    }
}
