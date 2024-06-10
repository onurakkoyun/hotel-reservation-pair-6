package com.tobeto.hotel_reservation_pair_6.services.concretes;

import com.tobeto.hotel_reservation_pair_6.core.services.concretes.JwtService;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.Manager;
import com.tobeto.hotel_reservation_pair_6.entities.enums.Role;
import com.tobeto.hotel_reservation_pair_6.repositories.ManagerRepository;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.AuthenticationService;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.ManagerService;
import com.tobeto.hotel_reservation_pair_6.services.dtos.authDtos.responses.AuthenticationResponse;
import com.tobeto.hotel_reservation_pair_6.services.dtos.managerDtos.requests.RegisterManagerRequest;
import com.tobeto.hotel_reservation_pair_6.services.mappers.ManagerMapper;
import com.tobeto.hotel_reservation_pair_6.services.rules.abstracts.UserBusinessRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService{

    private final JwtService jwtService;
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
}
