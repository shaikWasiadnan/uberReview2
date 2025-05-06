package com.example.uberReview2.controllers;

import com.example.uberReview2.models.Review;
import com.example.uberReview2.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    public ReviewService reviewService;

    public ReviewController(ReviewService reviewService){
        this.reviewService=reviewService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getReviewById(@PathVariable Long id){
        try{
            Optional<Review> review=reviewService.findReviewById(id);
            return new ResponseEntity<>(review,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(){
        try{
            List<Review> reviews= reviewService.findAllReviews();
            return new ResponseEntity<>(reviews,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(List.of(),HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping()
    public ResponseEntity<Review> publishReview(@RequestBody Review review){
        Review addedReview=reviewService.publishReview(review);
        return new ResponseEntity<>(addedReview,HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReviewById(@PathVariable Long id){
        try{
            boolean isDeleted= reviewService.deleteReviewById(id);
            if (isDeleted == false) {
                return new ResponseEntity<>("Cant delete due to some error",HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>("Successfully deleted",HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>("Error Occured while deleting",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateReviewById(@PathVariable Long id,@RequestBody Review review){
        try{
            Review review1=reviewService.updateReview(id,review);
            return new ResponseEntity<>(review1,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>("cant update due to some error",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
