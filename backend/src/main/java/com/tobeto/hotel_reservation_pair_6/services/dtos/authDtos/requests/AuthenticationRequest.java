package com.tobeto.hotel_reservation_pair_6.services.dtos.authDtos.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    @NotNull(message = "E-mail required!")
    private String email;

    @NotNull(message = "E-mail required!")
    private String password;
}
