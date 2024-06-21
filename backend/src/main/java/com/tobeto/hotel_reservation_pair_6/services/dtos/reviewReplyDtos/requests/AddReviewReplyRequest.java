package com.tobeto.hotel_reservation_pair_6.services.dtos.reviewReplyDtos.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddReviewReplyRequest {

    @NotNull(message = "Response message required!")
    private String response;

    private long managerId;

    private long reviewId;
}
