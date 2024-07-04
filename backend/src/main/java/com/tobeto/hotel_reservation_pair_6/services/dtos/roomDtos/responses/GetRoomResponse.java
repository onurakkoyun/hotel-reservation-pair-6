package com.tobeto.hotel_reservation_pair_6.services.dtos.roomDtos.responses;

import com.iyzipay.model.Currency;
import com.tobeto.hotel_reservation_pair_6.services.dtos.roomBedDtos.responses.GetRoomBedResponse;
import com.tobeto.hotel_reservation_pair_6.services.dtos.roomFeatureDtos.responses.GetRoomFeatureResponse;
import com.tobeto.hotel_reservation_pair_6.services.dtos.roomImageDtos.responses.GetRoomImageResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetRoomResponse {

    private long id;

    private int quantity;

    private double dailyPrice;

    private Currency currency;

    private int capacity;

    private int squareMeterSize;

    private int hotelId;

    private int roomTypeId;

    private String roomTypeName;

    private List<GetRoomImageResponse> roomImages;

    private List<GetRoomBedResponse> roomBeds;

    private List<GetRoomFeatureResponse> roomFeatures;

}
