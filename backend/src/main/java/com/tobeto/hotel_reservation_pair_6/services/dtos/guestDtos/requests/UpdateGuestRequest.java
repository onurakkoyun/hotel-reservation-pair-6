package com.tobeto.hotel_reservation_pair_6.services.dtos.guestDtos.requests;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateGuestRequest {
    //TODO: ProfilePhotoUrl'i cloudinary'e eklemeyi configure et.

    private long id;

    @NotNull(message = "First name cannot be empty.")
    private String firstName;

    @NotNull(message = "Last name cannot be empty.")
    private String lastName;

    private String phoneNumber;

    private MultipartFile profilePhoto;

    private String firstAddress;

    private String secondAddressLine;

    private String country;

    private String province;

    private String city;

    private String postalCode;
}
