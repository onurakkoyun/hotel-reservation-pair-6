package com.tobeto.hotel_reservation_pair_6.services.dtos.roomBedDtos.requests;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssignRoomBedRequest {
    private int bedId;

    @Positive(message = "Quantity value must be positive!")
    private int quantity;
}
