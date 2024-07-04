package com.tobeto.hotel_reservation_pair_6.services.dtos.userDtos.responses;

import com.tobeto.hotel_reservation_pair_6.entities.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetUserResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String profilePhotoUrl;

    private String firstAddress;

    private String secondAddressLine;

    private String country;

    private String province;

    private String city;

    private String postalCode;

    private Role role;
}
