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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService{
    private final ManagerRepository managerRepository;
    private final UserBusinessRuleService userBusinessRuleService;

    @Override
    public Manager save(Manager manager) {
        return managerRepository.save(manager);
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

        String profilePhoto = userBusinessRuleService.handleProfilePhotoChange
                (existingManager.getId(), request.getProfilePhoto());

        existingManager.setProfilePhotoUrl(profilePhoto);


        ManagerMapper.INSTANCE.mapUpdateManagerRequestToManager(request, existingManager);

        managerRepository.save(existingManager);

        return new SuccessResult("Manager updated.");
    }
}
