package com.tobeto.hotel_reservation_pair_6.services.dtos.managerDtos.requests;

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
public class RegisterManagerRequest {

    @Email(message = "Enter a valid email format.")
    private String email;

    @NotNull(message = "Password cannot be empty.")
    private String password;

    @NotNull(message = "Re-password cannot be empty.")
    private String passwordConfirm;

    @NotNull(message = "First name cannot be empty.")
    private String firstName;

    @NotNull(message = "Last name cannot be empty.")
    private String lastName;

    @NotNull(message = "Phone number cannot be empty.")
    //@Pattern(regexp = "^\\\\+905[0-9]{2}-[0-9]{3}-[0-9]{4}$", message = "Enter a valid mobile phone number (Example: +905XX-XXX-XXXX)")
    private String phoneNumber;
}
