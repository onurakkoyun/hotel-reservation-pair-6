package com.tobeto.hotel_reservation_pair_6.services.concretes;

import com.tobeto.hotel_reservation_pair_6.core.results.Result;
import com.tobeto.hotel_reservation_pair_6.core.results.SuccessResult;
import com.tobeto.hotel_reservation_pair_6.core.services.concretes.CloudinaryService;
import com.tobeto.hotel_reservation_pair_6.core.services.concretes.JwtService;
import com.tobeto.hotel_reservation_pair_6.core.utilities.exceptions.types.BusinessException;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.Manager;
import com.tobeto.hotel_reservation_pair_6.entities.enums.Role;
import com.tobeto.hotel_reservation_pair_6.repositories.ManagerRepository;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.AuthenticationService;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.ManagerService;
import com.tobeto.hotel_reservation_pair_6.services.dtos.authDtos.responses.AuthenticationResponse;
import com.tobeto.hotel_reservation_pair_6.services.dtos.managerDtos.requests.RegisterManagerRequest;
import com.tobeto.hotel_reservation_pair_6.services.dtos.managerDtos.requests.UpdateManagerRequest;
import com.tobeto.hotel_reservation_pair_6.services.mappers.ManagerMapper;
import com.tobeto.hotel_reservation_pair_6.services.rules.abstracts.UserBusinessRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService{

    private final JwtService jwtService;
    private final CloudinaryService cloudinaryService;
    private final ManagerRepository managerRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserBusinessRuleService userBusinessRuleService;
    private final AuthenticationService authenticationService;

    @Override
    public AuthenticationResponse register(RegisterManagerRequest request) {
        userBusinessRuleService.checkIfEmailAlreadyExists(request.getEmail());

        userBusinessRuleService.checkIfPasswordsMatch(request.getPassword(), request.getPasswordConfirm());

        Manager manager = ManagerMapper.INSTANCE.mapRegisterManagerRequestToManager(request);
        manager.setPassword(passwordEncoder.encode(request.getPassword()));
        manager.setRole(Role.MANAGER);

        var savedManager = managerRepository.save(manager);
        var jwtToken = jwtService.generateToken(manager);
        var refreshToken = jwtService.generateRefreshToken(manager);

        authenticationService.saveUserToken(savedManager, jwtToken);

        return AuthenticationResponse.builder().accessToken(jwtToken).refreshToken(refreshToken).build();
    }

    @Override
    public Result update(UpdateManagerRequest request) throws IOException {
        Manager existingManager = managerRepository.findById(request.getId())
                        .orElseThrow(() -> new BusinessException("Manager not found."));

        // Burada istersek api ile birlikte gönderilen JWT token'a ait Manager'ın, güncellenmek istenen Manager
        // ile aynı Manager olup olmadığını kontrol edebiliriz.
        /*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        if (!currentUsername.equals(existingManager.getEmail())) {
            throw new SecurityException("You are not allowed to update another manager's profile.");
        }*/

        if (request.getProfilePhoto() != null && !request.getProfilePhoto().isEmpty()) {
            if (existingManager.getProfilePhotoUrl() != null && !existingManager.getProfilePhotoUrl().isEmpty()) {
                String publicId = cloudinaryService.getPublicIdFromUrl(existingManager.getProfilePhotoUrl());
                cloudinaryService.deleteFile(publicId);
            }
            String profilePhotoUrl = cloudinaryService.uploadFile(request.getProfilePhoto());
            existingManager.setProfilePhotoUrl(profilePhotoUrl);
        }

        ManagerMapper.INSTANCE.mapUpdateManagerRequestToManager(request, existingManager);

        managerRepository.save(existingManager);

        return new SuccessResult("Manager updated.");
    }
}
