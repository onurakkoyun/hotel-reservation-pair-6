package com.tobeto.hotel_reservation_pair_6.services.dtos.roomDtos.responses;

import com.tobeto.hotel_reservation_pair_6.services.dtos.roomBedDtos.responses.GetRoomBedResponse;
import com.tobeto.hotel_reservation_pair_6.services.dtos.roomFeatureDtos.responses.GetRoomFeatureResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAvailableRoomResponse {

    private long id;

    private int quantity;

    private int bookedRoomQuantity;

    private double dailyPrice;

    private int capacity;

    private int squareMeterSize;

    private int hotelId;

    private int roomTypeId;

    private List<GetRoomBedResponse> roomBeds;

    private List<GetRoomFeatureResponse> roomFeatures;

}
