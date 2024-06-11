package za.ac.cput.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Review;
import za.ac.cput.service.ReviewService;
import java.util.List;
@RestController
@RequestMapping("/Review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @PostMapping("/save")
    public Review save(@RequestBody Review review){
        return reviewService.create(review);
    }
    @GetMapping("/read/{ReviewId}")
    public Review read(@PathVariable String reviewID){
        return reviewService.read(reviewID);
    }
    @DeleteMapping("/delete/{ReviewId}")
    public void delete (@PathVariable String reviewID){
        reviewService.delete(reviewID);
    }
    @PutMapping("/update")
    public Review update(@RequestBody Review review) {
        return reviewService.update(review);
    }
    @GetMapping("/getAllReviews")
    public List<Review> getAllReviews(){
        return reviewService.getAllReviews();
    }
}
