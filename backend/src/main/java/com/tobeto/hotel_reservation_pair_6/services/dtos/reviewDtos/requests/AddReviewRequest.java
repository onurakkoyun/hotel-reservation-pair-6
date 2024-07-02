package com.tobeto.hotel_reservation_pair_6.services.dtos.reviewDtos.requests;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddReviewRequest {

    @NotNull(message = "Comment required!")
    private String comment;

    @NotNull(message = "Rating required.")
    @Range(min = 1, max = 10, message = "Rating could be between 1 and 10.")
    private int rating;

    private long guestId;

    private int hotelId;
}
