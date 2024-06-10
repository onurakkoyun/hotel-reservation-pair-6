package com.tobeto.hotel_reservation_pair_6.services.dtos.guestDtos.requests;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateGuestRequest {

    private long id;

    @NotNull(message = "First name cannot be empty.")
    private String firstName;

    @NotNull(message = "Last name cannot be empty.")
    private String lastName;

    private String phoneNumber;

    private String profilePhotoUrl;

    private String firstAddress;

    private String secondAddressLine;

    private String country;

    private String province;

    private String city;

    private String postalCode;
}
