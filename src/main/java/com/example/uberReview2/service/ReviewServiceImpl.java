package com.example.uberReview2.service;

import com.example.uberReview2.models.Review;
import com.example.uberReview2.repository.ReviewRepository;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;
import java.util.Optional;



public class ReviewServiceImpl implements ReviewService{
    private final Logger logger=LoggerFactory.getLogger(ReviewServiceImpl.class);
    public ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository){
        this.reviewRepository=reviewRepository;
    }
    @Override
    public Optional<Review> findReviewById(Long id){
        logger.info("Searching for review with id: "+id);
        try{
            Optional<Review> review=reviewRepository.findById(id);
            if(review.isEmpty()){
                logger.warn("Review with id: "+ id +" not found");
            }
            return review;
        }
        catch (Exception e){
            logger.error("Exception occured while fetching review with id: "+id);
            return Optional.empty();
        }

    }

    @Override
    public List<Review> findAllReviews() {
        logger.info("Finding all reviews");
        try{
            List<Review> allReviews=reviewRepository.findAll();
            if(allReviews.isEmpty()){
                logger.warn("list of reviews is empty");
            }
            return allReviews;
        }
        catch (Exception e){
            logger.error("Exception occured while fetching all reviews",e);
            return List.of();
        }
    }

    @Override
    public Review publishReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public boolean deleteReviewById(Long id) {
        try{
            Review review=reviewRepository.findById(id).orElseThrow(EntityNotFoundException::new);
            reviewRepository.delete(review);
            return true;
        }
        catch (Exception e){
            logger.error("Error occured while deleting review with id: "+id,e);
            return false;
        }
    }

    @Override
    public Review updateReview(Long id, Review review) {
        try{
            Review review1=reviewRepository.findById(id).orElseThrow(
                    ()->new EntityNotFoundException("Review with id: "+id+" not found")
            );
            if(review.getContent()!=null){
                review1.setContent(review.getContent());
            }
            if(review.getRating()!=null){
                review1.setRating(review.getRating());
            }
            logger.info("Successfully updated the review with id: "+id);
            reviewRepository.save(review1);
            return review1;
        }
        catch (Exception e) {
            logger.error("Error occured while updating");
            throw new RuntimeException("Failed to update",e);
        }

    }
}
