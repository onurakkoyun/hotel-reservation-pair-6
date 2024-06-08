package com.tobeto.hotel_reservation_pair_6.services.dtos.hotelDtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetHotelFeatureResponse {
    private int id;
    private String hotelFeatureName;
}
