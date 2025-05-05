package com.example.uberReview2.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Passenger extends Base{

    private String name;

    @OneToMany(mappedBy = "passenger")
    private List<Booking> bookings=new ArrayList<>();
}
