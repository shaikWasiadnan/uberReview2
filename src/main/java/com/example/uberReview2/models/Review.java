package com.example.uberReview2.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "booking_review")
@JsonIgnoreProperties("booking")
public class Review extends Base{


    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Double rating;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Booking booking;






}
