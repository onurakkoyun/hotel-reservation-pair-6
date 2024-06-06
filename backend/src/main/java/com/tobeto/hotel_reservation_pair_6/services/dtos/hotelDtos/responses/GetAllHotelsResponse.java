package com.tobeto.hotel_reservation_pair_6.services.dtos.hotelDtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllHotelsResponse {
    //TODO:Otele ait photolarda eklenecek.

    private int id;

    private int managerId;

    private String hotelName;

    private boolean isBreakfastAvailable;

    private boolean IsBreakfastIncludedInPrice;

    private double breakfastPricePerPerson;

    private int starCount;

    private String firstAddressLine;

    private String secondAddressLine;

    private String city;

    private String postalCode;

    private String province;

    private String country;

    List<GetHotelFeatureResponse> hotelFeatures;
}
