package com.example.uberReview2.adapters;

import com.example.uberReview2.dtos.CreateReviewDto;
import com.example.uberReview2.models.Review;
import org.springframework.stereotype.Component;

@Component
public interface ConvertReviewDtoToReviewAdapter {
    public Review convertToReview(CreateReviewDto createReviewDto);
}
