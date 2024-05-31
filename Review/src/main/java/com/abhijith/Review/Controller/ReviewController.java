package com.abhijith.Review.Controller;

import com.abhijith.Review.Exception.CompanyException;
import com.abhijith.Review.Exception.ReviewException;
import com.abhijith.Review.Model.Review;
import com.abhijith.Review.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    @GetMapping("")
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam("companyId") Long companyId) throws ReviewException {
        List<Review> reviews = reviewService.findReviewsByCompanyId(companyId);
        if (reviews.isEmpty()){
            throw new ReviewException("Review not found");
        }
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
    @PostMapping("")
    public  ResponseEntity<String> addReview(@RequestParam("companyId") Long companyId, @RequestBody Review review) throws ReviewException {
        if(!reviewService.addReview(companyId,review)){
            throw new ReviewException("Review not Added");
        }
        return ResponseEntity.ok("Review Added");

    }
    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getJob(@PathVariable("reviewId") Long id) throws CompanyException, ReviewException {
        Review review = reviewService.findReviewById(id);
        if(review == null){
            throw new ReviewException("Review Not Exists");
        }
        return ResponseEntity.ok(review);
    }
    @DeleteMapping("/{reviewId}")
    public  ResponseEntity<String> deleteReview(@PathVariable("reviewId") Long id) throws ReviewException, CompanyException {
        boolean status= reviewService.deleteReview(id);
        if(!status) {
            throw  new ReviewException("Review Deletion didn't happen");
        }
        return ResponseEntity.ok("Review deleted");
    }
    @PutMapping("/{reviewId}")
    public  ResponseEntity<String> updateReview(@RequestBody Review review, @PathVariable("reviewId") Long reviewId) throws ReviewException, CompanyException {
        if(!reviewService.updateReview(reviewId, review)){
            throw new ReviewException("Review not updated");
        }

        return ResponseEntity.ok("Review updated");
    }
}
