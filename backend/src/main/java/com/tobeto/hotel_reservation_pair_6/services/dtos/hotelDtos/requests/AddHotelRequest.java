package com.tobeto.hotel_reservation_pair_6.services.dtos.hotelDtos.requests;

import com.tobeto.hotel_reservation_pair_6.services.dtos.hotelFeatureDtos.requests.AssignHotelFeatureRequest;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddHotelRequest {

    @NotNull(message = "Hotel c")
    private String hotelName;

    @NotNull
    private int managerId;

    @NotNull(message = "Breakfast information must be selected.")
    private boolean isBreakfastAvailable;

    private boolean IsBreakfastIncludedInPrice;

    private double breakfastPricePerPerson;

    private int starCount;

    @NotNull(message = "Hotel address line can not be empty.")
    private String firstAddressLine;

    private String secondAddressLine;

    @NotNull(message = "Hotel city can not be empty.")
    private String city;

    @NotNull(message = "Hotel postal code can not be empty.")
    private String postalCode;

    @NotNull(message = "Hotel province can not be empty.")
    private String province;

    @NotNull(message = "Hotel country can not be empty.")
    private String country;

    List<AssignHotelFeatureRequest> hotelFeatures;
}
