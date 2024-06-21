package com.tobeto.hotel_reservation_pair_6.services.rules.concretes;

import com.tobeto.hotel_reservation_pair_6.core.services.concretes.CloudinaryService;
import com.tobeto.hotel_reservation_pair_6.core.utilities.exceptions.types.BusinessException;
import com.tobeto.hotel_reservation_pair_6.repositories.UserRepository;
import com.tobeto.hotel_reservation_pair_6.services.rules.abstracts.UserBusinessRuleService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class UserBusinessRuleImpl implements UserBusinessRuleService {
    private final UserRepository userRepository;

    private final CloudinaryService cloudinaryService;

    public void checkIfEmailAlreadyExists(String email) {
        this.userRepository.findByEmail(email).ifPresent(user -> {
            throw new BusinessException(user.getEmail() + " is not available.");
        });
    }

    @Override
    public void checkIfPasswordsMatch(String password, String passwordConfirm) {
        if(!password.equals(passwordConfirm)) {
            throw new BusinessException("Passwords does not match.");
        }
    }

    @Override
    public String handleProfilePhotoChange(long userId, MultipartFile profilePhoto) throws IOException {
        var existingUser = this.userRepository.findById(userId);

        if (profilePhoto != null && !profilePhoto.isEmpty()) {
            if (existingUser.get().getProfilePhotoUrl() != null &&
                    !existingUser.get().getProfilePhotoUrl().isEmpty()) {
                String publicId = cloudinaryService.getPublicIdFromUrl(existingUser.get().getProfilePhotoUrl());
                cloudinaryService.deleteFile(publicId);
            }
            String profilePhotoUrl = cloudinaryService.uploadFile(profilePhoto);
            return profilePhotoUrl;
        }

        return null;
    }

}
