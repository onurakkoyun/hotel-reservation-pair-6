package com.tobeto.hotel_reservation_pair_6.services.dtos.hotelDtos.requests;

import com.tobeto.hotel_reservation_pair_6.entities.concretes.HotelImage;
import com.tobeto.hotel_reservation_pair_6.services.dtos.hotelFeatureDtos.requests.AssignHotelFeatureRequest;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddHotelRequest {

    @NotNull(message = "Hotel c")
    private String hotelName;

    @NotNull
    private long managerId;

    @NotNull(message = "Breakfast information required!")
    private boolean isBreakfastAvailable;

    private boolean IsBreakfastIncludedInPrice;

    private double breakfastPricePerPerson;

    private int starCount;

    @NotNull(message = "Address line required!")
    private String firstAddressLine;

    private String secondAddressLine;

    @NotNull(message = "City required!")
    private String city;

    @NotNull(message = "Postal code required!")
    private String postalCode;

    @NotNull(message = "Province required!")
    private String province;

    @NotNull(message = "Country required!")
    private String country;

    private MultipartFile[] photos;

    private List<AssignHotelFeatureRequest> hotelFeatures;
}
