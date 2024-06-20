package com.tobeto.hotel_reservation_pair_6.services.dtos.reviewDtos.responses;


import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetReviewsResponse {

    private LocalDateTime creationDate;

    @Length(min = 1, max = 255, message = "Comment length must be between 1 and 255 characters.")
    private String comment;

    @Size(min = 1, max = 10)
    private int rating;

    private long guestId;

    private int hotelId;
}
