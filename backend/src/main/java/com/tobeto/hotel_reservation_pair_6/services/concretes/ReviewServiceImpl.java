package com.tobeto.hotel_reservation_pair_6.services.concretes;

import com.tobeto.hotel_reservation_pair_6.core.results.ErrorResult;
import com.tobeto.hotel_reservation_pair_6.core.results.Result;
import com.tobeto.hotel_reservation_pair_6.core.results.SuccessResult;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.Hotel;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.Review;
import com.tobeto.hotel_reservation_pair_6.repositories.ReviewRepository;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.HotelService;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.ReservationService;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.ReviewService;
import com.tobeto.hotel_reservation_pair_6.services.dtos.reviewDtos.requests.AddReviewRequest;
import com.tobeto.hotel_reservation_pair_6.services.dtos.reviewDtos.responses.GetReviewResponse;
import com.tobeto.hotel_reservation_pair_6.services.mappers.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;

    private final HotelService hotelService;

    private final ReservationService reservationService;

    @Override
    public Result add(AddReviewRequest request) {

        if (reservationService.findReservationsByHotelIdAndGuestId(request.getHotelId(),
                request.getGuestId()).isEmpty()){
            return new ErrorResult("The guest's reservation was not found in the hotel's reservations.");
        }

        Review review = ReviewMapper.INSTANCE.mapAddReviewRequestToReview(request);
        review.setCreationDate(LocalDateTime.now());

        reviewRepository.save(review);

        updateHotelRatingAverage(review.getHotel().getId());

        return new SuccessResult("Review added.");
    }

    @Override
    public List<GetReviewResponse> getReviwsByHotelId(int hotelId) {
        List<Review> reviews = reviewRepository.findAllByHotel_Id(hotelId);

        return reviews.stream().map(review -> {
            GetReviewResponse response = ReviewMapper.INSTANCE.mapReviewToGetReviewsResponse(review);
            return response;
        }).collect(Collectors.toList());
    }

    @Override
    public void updateHotelRatingAverage(int hotelId) {
        Double averageRating = reviewRepository.findAverageRatingByHotelId(hotelId);

        if (averageRating != null) {
            Hotel hotel = hotelService.findById(hotelId);
            hotel.setRatingAverage(averageRating);
            hotelService.save(hotel);
        }
    }
}
