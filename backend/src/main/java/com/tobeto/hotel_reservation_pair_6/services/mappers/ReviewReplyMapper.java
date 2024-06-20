package com.tobeto.hotel_reservation_pair_6.services.mappers;

import com.tobeto.hotel_reservation_pair_6.entities.concretes.ReviewReply;
import com.tobeto.hotel_reservation_pair_6.services.dtos.reviewReplyDtos.requests.AddReviewReplyRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReviewReplyMapper {

    ReviewReplyMapper INSTANCE =  Mappers.getMapper(ReviewReplyMapper.class);

    @Mapping(source = "managerId", target = "manager.id")
    @Mapping(source = "reviewId", target = "review.id")
    ReviewReply mapAddReviewReplyRequestToReviewReply(AddReviewReplyRequest request);
}
