package com.example.uberReview2.adapters;

import com.example.uberReview2.dtos.CreateReviewDto;
import com.example.uberReview2.models.Booking;
import com.example.uberReview2.models.Review;
import com.example.uberReview2.repository.BookingRepository;
import lombok.Builder;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Optional;
@Component

public class ConvertReviewDtoToReviewAdapterImpl implements ConvertReviewDtoToReviewAdapter{

    private BookingRepository bookingRepository;

    public  ConvertReviewDtoToReviewAdapterImpl(BookingRepository bookingRepository){
        this.bookingRepository=bookingRepository;
    }
    @Override
    public Review convertToReview(CreateReviewDto createReviewDto) {
        Optional<Booking> booking=bookingRepository.findById(createReviewDto.getBooking_id());
        if (booking.isEmpty()){
            return null;
        }
        Review review= Review.builder()
                .content(createReviewDto.getContent())
                .rating(createReviewDto.getRating())
                .booking(booking.get())
                .build();

        return review;
    }
}
