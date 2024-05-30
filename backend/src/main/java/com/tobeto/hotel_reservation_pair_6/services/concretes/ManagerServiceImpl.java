package com.tobeto.hotel_reservation_pair_6.services.concretes;

import com.tobeto.hotel_reservation_pair_6.core.results.Result;
import com.tobeto.hotel_reservation_pair_6.core.services.concretes.JwtService;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.User;
import com.tobeto.hotel_reservation_pair_6.entities.enums.Role;
import com.tobeto.hotel_reservation_pair_6.repositories.UserRepository;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.AuthenticationService;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.ManagerService;
import com.tobeto.hotel_reservation_pair_6.services.dtos.authDtos.responses.AuthenticationResponse;
import com.tobeto.hotel_reservation_pair_6.services.dtos.managerDtos.requests.RegisterManagerRequest;
import com.tobeto.hotel_reservation_pair_6.services.mappers.UserMapper;
import com.tobeto.hotel_reservation_pair_6.services.rules.abstracts.UserBusinessRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService{

    private final JwtService jwtService;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final UserBusinessRuleService userBusinessRuleService;
    private final AuthenticationService authenticationService;

    @Override
    public AuthenticationResponse register(RegisterManagerRequest request) {
        userBusinessRuleService.checkIfEmailAlreadyExists(request.getEmail());

        userBusinessRuleService.checkIfPasswordsMatch(request.getPassword(), request.getPasswordConfirm());

        User user = UserMapper.INSTANCE.mapRegisterManagerRequestToUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.MANAGER);

        var savedUser = repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        authenticationService.saveUserToken(savedUser, jwtToken);


        return AuthenticationResponse.builder().accessToken(jwtToken).refreshToken(refreshToken).build();
    }
}
