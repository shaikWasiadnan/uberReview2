package com.example.uberReview2.controllers;

import com.example.uberReview2.adapters.ConvertReviewDtoToReviewAdapter;
import com.example.uberReview2.dtos.CreateReviewDto;
import com.example.uberReview2.dtos.ReviewDto;
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
    private ReviewService reviewService;
    private ConvertReviewDtoToReviewAdapter convertReviewDtoToReviewAdapter;


    public ReviewController(ReviewService reviewService,ConvertReviewDtoToReviewAdapter convertReviewDtoToReviewAdapter){
        this.reviewService=reviewService;
        this.convertReviewDtoToReviewAdapter=convertReviewDtoToReviewAdapter;
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getReviewById(@PathVariable Long id){
        try{
            Optional<Review> review=reviewService.findReviewById(id);
            if(review.isEmpty()){
                return new ResponseEntity<>("Review with id is not present: "+id,HttpStatus.NOT_FOUND);

            }
            return new ResponseEntity<>(review,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping
    public ResponseEntity<Object> getAllReviews(){
        try{
            List<Review> reviews= reviewService.findAllReviews();
            if(reviews.isEmpty()){
                return new ResponseEntity<>("You don't have reviews yet",HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(reviews,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(List.of(),HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping()
    public ResponseEntity<?> publishReview(@RequestBody CreateReviewDto review){
        Review incomingReview=this.convertReviewDtoToReviewAdapter.convertToReview(review);
        if(incomingReview ==null){
            return new ResponseEntity<>("review is null",HttpStatus.BAD_REQUEST);
        }
        Review addedReview=this.reviewService.publishReview(incomingReview);
//        ReviewDto response=ReviewDto.builder()
//                .id(addedReview.getId())
//                .content(addedReview.getContent())
//                .rating(addedReview.getRating())
//                .created_at(addedReview.getCreatedAt())
//                .updated_at(addedReview.getUpdatedAt())
//                .booking_id(addedReview.getBooking().getId())
//                .build();
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
