package com.tobeto.hotel_reservation_pair_6.services.dtos.guestDtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterGuestRequest {
    @NotNull(message = "First name required!")
    private String firstName;

    @NotNull(message = "Last name required!")
    private String lastName;

    @NotNull(message = "E-mail required!")
    @Email(message = "Not a valid e-mail format!")
    private String email;

    @NotNull(message = "Password required!")
    private String password;

    @NotNull(message = "Password confirmation required!")
    private String passwordConfirm;

    @NotNull(message = "Phone number required!")
    //@Pattern(regexp = "^\\\\+905[0-9]{2}-[0-9]{3}-[0-9]{4}$", message = "Enter a valid mobile phone number (Example: +905XX-XXX-XXXX)")
    private String phoneNumber;
}
