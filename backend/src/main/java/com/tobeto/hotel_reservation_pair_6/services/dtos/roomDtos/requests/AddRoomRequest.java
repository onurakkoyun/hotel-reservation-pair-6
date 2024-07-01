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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddRoomRequest {

    @Positive(message = "Quantity value must be positive!")
    private int quantity;

    @Positive(message = "Daily price must be positive!")
    private double dailyPrice;

    @NotNull(message = "Currency required!")
    private Currency currency;

    @Positive(message = "Capacity required!")
    private int capacity;

    @Positive(message = "Square meter must be positive!")
    private int squareMeterSize;

    private int hotelId;

    private int roomTypeId;

    private MultipartFile[] photos;

    List<AssignRoomBedRequest> roomBeds;

    List<AssignRoomFeatureRequest> roomFeatures;
}
