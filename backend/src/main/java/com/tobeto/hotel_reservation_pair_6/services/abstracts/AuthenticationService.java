package com.tobeto.hotel_reservation_pair_6.services.abstracts;

import com.tobeto.hotel_reservation_pair_6.entities.concretes.User;
import com.tobeto.hotel_reservation_pair_6.services.dtos.authDtos.requests.AuthenticationRequest;
import com.tobeto.hotel_reservation_pair_6.services.dtos.authDtos.responses.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);

    void saveUserToken(User user, String jwtToken);

    void revokeAllUserTokens(User user);

    void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException;
}
