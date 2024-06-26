package com.tobeto.hotel_reservation_pair_6.services.dtos.roomBedDtos.responses;

import com.tobeto.hotel_reservation_pair_6.entities.concretes.Bed;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetRoomBedResponse {

    private int id;

    private int quantity;

    private String bedName;
}
