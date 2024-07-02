package com.tobeto.hotel_reservation_pair_6.services.mappers;

import com.tobeto.hotel_reservation_pair_6.entities.concretes.Review;
import com.tobeto.hotel_reservation_pair_6.services.dtos.reviewDtos.requests.AddReviewRequest;
import com.tobeto.hotel_reservation_pair_6.services.dtos.reviewDtos.responses.GetReviewResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReviewMapper {
    ReviewMapper INSTANCE =  Mappers.getMapper(ReviewMapper.class);

    @Mapping(source = "guestId", target = "guest.id")
    @Mapping(source = "hotelId", target = "hotel.id")
    Review mapAddReviewRequestToReview(AddReviewRequest addReviewRequest);

    @Mapping(source = "guest.id", target = "guestId")
    @Mapping(source = "hotel.id", target = "hotelId")
    GetReviewResponse mapReviewToGetReviewsResponse(Review review);
}
