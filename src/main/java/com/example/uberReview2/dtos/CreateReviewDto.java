package com.example.uberReview2.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateReviewDto {
    private String content;

    private Double rating;

    private Long booking_id;

}
