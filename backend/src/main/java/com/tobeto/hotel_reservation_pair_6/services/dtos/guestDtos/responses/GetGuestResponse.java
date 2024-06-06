package com.tobeto.hotel_reservation_pair_6.services.dtos.guestDtos.responses;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetGuestResponse {
    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;
}
