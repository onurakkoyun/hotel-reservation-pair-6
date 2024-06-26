package com.tobeto.hotel_reservation_pair_6.services.dtos.roomDtos.requests;

import com.iyzipay.model.Currency;
import com.tobeto.hotel_reservation_pair_6.services.dtos.roomBedDtos.requests.AssignRoomBedRequest;
import com.tobeto.hotel_reservation_pair_6.services.dtos.roomFeatureDtos.requests.AssignRoomFeatureRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
public class UpdateRoomRequest {

    private long id;

    @Positive(message = "Quantity value must be positive!")
    private int quantity;

    @Positive(message = "Daily price must be positive!")
    private double dailyPrice;

    @NotNull(message = "Currency required!")
    private Currency currency;

    @Positive(message = "Capacity must be positive!")
    private int capacity;

    @Positive(message = "Square meter must be positive!")
    private int squareMeterSize;

    private int hotelId;

    private int roomTypeId;

    List<AssignRoomBedRequest> roomBeds;

    List<AssignRoomFeatureRequest> roomFeatures;
}
