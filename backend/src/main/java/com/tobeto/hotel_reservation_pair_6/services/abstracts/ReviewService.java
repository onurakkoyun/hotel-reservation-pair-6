package com.tobeto.hotel_reservation_pair_6.services.abstracts;

import com.tobeto.hotel_reservation_pair_6.core.results.Result;
import com.tobeto.hotel_reservation_pair_6.services.dtos.reviewDtos.requests.AddReviewRequest;
import com.tobeto.hotel_reservation_pair_6.services.dtos.reviewDtos.responses.GetReviewResponse;

import java.util.List;

public interface ReviewService {
    Result add(AddReviewRequest request);

    List<GetReviewResponse> getReviwsByHotelId(int hotelId);

    void updateHotelRatingAverage(int hotelId);
}
