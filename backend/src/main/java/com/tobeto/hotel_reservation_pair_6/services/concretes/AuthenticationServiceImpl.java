package com.tobeto.hotel_reservation_pair_6.services.concretes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tobeto.hotel_reservation_pair_6.core.services.concretes.JwtService;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.Guest;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.Manager;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.Token;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.User;
import com.tobeto.hotel_reservation_pair_6.entities.enums.Role;
import com.tobeto.hotel_reservation_pair_6.entities.enums.TokenType;
import com.tobeto.hotel_reservation_pair_6.repositories.TokenRepository;
import com.tobeto.hotel_reservation_pair_6.repositories.UserRepository;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.AuthenticationService;

import com.tobeto.hotel_reservation_pair_6.services.abstracts.GuestService;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.ManagerService;
import com.tobeto.hotel_reservation_pair_6.services.dtos.authDtos.requests.AuthenticationRequest;
import com.tobeto.hotel_reservation_pair_6.services.dtos.authDtos.responses.AuthenticationResponse;
import com.tobeto.hotel_reservation_pair_6.services.dtos.guestDtos.requests.RegisterGuestRequest;
import com.tobeto.hotel_reservation_pair_6.services.dtos.managerDtos.requests.RegisterManagerRequest;
import com.tobeto.hotel_reservation_pair_6.services.mappers.GuestMapper;
import com.tobeto.hotel_reservation_pair_6.services.mappers.ManagerMapper;
import com.tobeto.hotel_reservation_pair_6.services.rules.abstracts.UserBusinessRuleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final GuestService guestService;
    private final ManagerService managerService;
    private final UserBusinessRuleService userBusinessRuleService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = repository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);

        saveUserToken(user, jwtToken);

        return AuthenticationResponse.builder().accessToken(jwtToken).refreshToken(refreshToken).build();
    }

    @Override
    public AuthenticationResponse registerGuest(RegisterGuestRequest request) {
        userBusinessRuleService.checkIfEmailAlreadyExists(request.getEmail());

        userBusinessRuleService.checkIfPasswordsMatch(request.getPassword(), request.getPasswordConfirm());

        Guest guest = GuestMapper.INSTANCE.mapRegisterGuestRequestToGuest(request);
        guest.setPassword(passwordEncoder.encode(request.getPassword()));
        guest.setRole(Role.GUEST);

        var savedGuest = guestService.save(guest);
        var jwtToken = jwtService.generateToken(guest);
        var refreshToken = jwtService.generateRefreshToken(guest);

        saveUserToken(savedGuest, jwtToken);

        return AuthenticationResponse.builder().accessToken(jwtToken).refreshToken(refreshToken).build();
    }

    @Override
    public AuthenticationResponse registerManager(RegisterManagerRequest request) {
        userBusinessRuleService.checkIfEmailAlreadyExists(request.getEmail());

        userBusinessRuleService.checkIfPasswordsMatch(request.getPassword(), request.getPasswordConfirm());

        System.out.println();

        Manager manager = ManagerMapper.INSTANCE.mapRegisterManagerRequestToManager(request);
        manager.setPassword(passwordEncoder.encode(request.getPassword()));
        manager.setRole(Role.MANAGER);

        var savedManager = managerService.save(manager);
        var jwtToken = jwtService.generateToken(manager);
        var refreshToken = jwtService.generateRefreshToken(manager);

        saveUserToken(savedManager, jwtToken);

        return AuthenticationResponse.builder().accessToken(jwtToken).refreshToken(refreshToken).build();
    }

    @Override
    public void saveUserToken(User user, String jwtToken) {
        var token = Token.builder().user(user).token(jwtToken).tokenType(TokenType.BEARER).expired(false).revoked(false)
                .build();
        tokenRepository.save(token);
    }

    @Override
    public void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.repository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}
