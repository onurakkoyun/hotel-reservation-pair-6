package com.tobeto.hotel_reservation_pair_6.services.dtos.roomFeatureDtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetRoomFeatureResponse {
    private int id;
    private String featureName;
}
