package com.example.uberReview2.repository;

import com.example.uberReview2.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {

}
