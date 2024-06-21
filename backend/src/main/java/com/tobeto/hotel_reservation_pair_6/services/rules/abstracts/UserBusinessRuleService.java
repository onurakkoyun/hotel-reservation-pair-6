package com.tobeto.hotel_reservation_pair_6.services.rules.abstracts;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserBusinessRuleService {
    void checkIfEmailAlreadyExists(String email);

    void checkIfPasswordsMatch(String password, String passwordConfirm);

    String handleProfilePhotoChange(long userId, MultipartFile profilePhoto) throws IOException;
}
