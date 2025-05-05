package com.example.uberReview2.models;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "bookingReview")
public class Review extends Base{


    @Column(nullable = false)
    protected String content;

    @Column(nullable = false)
    protected Double rating;


}
