package com.tobeto.hotel_reservation_pair_6.services.concretes;

import com.tobeto.hotel_reservation_pair_6.core.results.Result;
import com.tobeto.hotel_reservation_pair_6.core.results.SuccessResult;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.Review;
import com.tobeto.hotel_reservation_pair_6.repositories.ReviewRepository;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.ReviewService;
import com.tobeto.hotel_reservation_pair_6.services.dtos.reviewDtos.requests.AddReviewRequest;
import com.tobeto.hotel_reservation_pair_6.services.dtos.reviewDtos.responses.GetReviewsResponse;
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

    @Override
    public Result add(AddReviewRequest request) {

        Review review = ReviewMapper.INSTANCE.mapAddReviewRequestToReview(request);
        review.setCreationDate(LocalDateTime.now());

        reviewRepository.save(review);

        return new SuccessResult("Review added.");
    }

    @Override
    public List<GetReviewsResponse> getReviwsByHotelId(int hotelId) {
        List<Review> reviews = reviewRepository.findAllByHotel_Id(hotelId);

        return reviews.stream().map(review -> {
            GetReviewsResponse response = ReviewMapper.INSTANCE.mapReviewToGetReviewsResponse(review);
            return response;
        }).collect(Collectors.toList());
    }
}
