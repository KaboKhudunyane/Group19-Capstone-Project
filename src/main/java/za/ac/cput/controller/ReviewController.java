package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.CarInformation;
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
        return reviewService.save(review);
    }

    @GetMapping("/read/{ReviewId}")
    public Review read(@PathVariable String ReviewId){
        return reviewService.read(ReviewId);
    }

    @DeleteMapping("/delete/{ReviewId}")
    public void delete (@PathVariable String ReviewId){
        reviewService.delete(ReviewId);}


    @GetMapping("/getall")
    public List<Review> getAll(){
        return reviewService.getCarReviewsAll();
    }
}
