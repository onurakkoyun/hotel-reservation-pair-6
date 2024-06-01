package com.tobeto.hotel_reservation_pair_6.services.dtos.roomBedDtos.requests;

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

    private int quantity;
}
