package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Booking;
import za.ac.cput.domain.Review;
import za.ac.cput.repository.ReviewRepository;

import java.util.List;
@Service
public class ReviewService implements IReviewService{
    private ReviewRepository repository;

    @Autowired
    ReviewService(ReviewRepository repository) {
        this.repository = repository;
    }

    @Override
    public Review save(Review review) {
        return repository.save(review);
    }

    @Override
    public ReviewService read(String reviewId) {
        return repository.findByReviewId(reviewId);
    }

    @Override
    public boolean delete(String reviewId) {
        Review review = repository.findByReviewId(reviewId);
        if (review != null) {
            repository.delete(review);
            return true;
        }
        return false;
    }

    @Override
    public List<Review> getAll() {
        return repository.findAll();
    }
}
