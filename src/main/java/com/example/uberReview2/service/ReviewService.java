package com.example.uberReview2.service;


import com.example.uberReview2.models.Review;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ReviewService  {
    public Optional<Review> findReviewById(Long id);

    public List<Review> findAllReviews();

    public Review publishReview(Review review);

    public boolean deleteReviewById(Long id);

    public Review updateReview(Long id,Review review);
}
