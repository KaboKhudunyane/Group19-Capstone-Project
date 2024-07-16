package za.ac.cput.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Review;
import za.ac.cput.repository.ReviewRepository;
import java.util.List;
@Service
public class ReviewService implements IService<Review, Long> {
    private final ReviewRepository repository;
    @Autowired
    public ReviewService(ReviewRepository repository) {
        this.repository = repository;
    }
    @Override
    public Review create(Review review) {
        return repository.save(review);
    }
    @Override
    public Review read(Long reviewID) {
        return repository.findByReviewID(reviewID);
    }
    @Override
    public Review update(Review review) {
        return repository.save(review);
    }
    @Override
    public void delete(Long reviewID) {
        repository.deleteByReviewID(reviewID);
    }
    @Override
    public List<Review> getAll() {
        return repository.findAll();
    }
}