package com.tobeto.hotel_reservation_pair_6.services.dtos.reviewDtos.requests;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddReviewRequest {

    @NotNull(message = "Comment required!")
    private String comment;

    @NotNull(message = "Rating required.")
    private int rating;

    private long guestId;

    private int hotelId;
}
