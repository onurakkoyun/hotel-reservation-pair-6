package com.tobeto.hotel_reservation_pair_6.services.dtos.hotelDtos.requests;

import com.tobeto.hotel_reservation_pair_6.services.dtos.hotelFeatureDtos.requests.AssignHotelFeatureRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddHotelRequest {
    @NotNull
    private int managerId;

    @NotNull(message = "Breakfast information must be selected")
    private boolean isBreakfastAvailable;
    private boolean IsBreakfastIncludedInPrice;
    private double breakfastPricePerPerson;
    private int starCount;
    List<AssignHotelFeatureRequest> hotelFeatures;
}
