package com.tobeto.hotel_reservation_pair_6.services.dtos.roomDtos.requests;

import com.iyzipay.model.Currency;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.RoomBed;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.RoomFeature;
import com.tobeto.hotel_reservation_pair_6.services.dtos.roomBedDtos.requests.AssignRoomBedRequest;
import com.tobeto.hotel_reservation_pair_6.services.dtos.roomFeatureDtos.requests.AssignRoomFeatureRequest;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddRoomRequest {

    @Positive(message = "Room quantity must be positive.")
    private int quantity;

    @NotNull(message = "Room price can not be null.")
    private double dailyPrice;

    @NotNull(message = "Room price currency can not be null.")
    private Currency currency;

    @Positive(message = "Room capacity must be positive.")
    private int capacity;

    @Positive(message = "Room square meter must be positive.")
    private int squareMeterSize;

    private int hotelId;

    private int roomTypeId;

    List<AssignRoomBedRequest> roomBeds;

    List<AssignRoomFeatureRequest> roomFeatures;
}
