package com.tobeto.hotel_reservation_pair_6.services.dtos.imageDtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetHotelImageResponse {
    private long id;

    private String url;

    private int hotelId;
}
