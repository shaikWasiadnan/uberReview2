package com.example.uberReview2.dtos;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private Long id;
    private String content;
    private Double rating;
    private Long booking_id;
    private Date created_at;
    private Date updated_at;
}
