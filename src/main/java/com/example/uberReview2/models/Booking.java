package com.example.uberReview2.models;

import jakarta.persistence.*;
import lombok.*;

import com.example.uberReview2.models.Driver;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Booking extends Base{
    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private Review review;

    @Enumerated(value = EnumType.STRING)
    private BookingStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date totalTime;

    @ManyToOne
    private Driver driver;

    @ManyToOne
    private Passenger passenger;
}
