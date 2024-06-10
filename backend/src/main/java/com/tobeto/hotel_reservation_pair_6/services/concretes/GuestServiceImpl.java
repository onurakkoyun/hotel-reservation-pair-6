package com.tobeto.hotel_reservation_pair_6.services.concretes;

import com.tobeto.hotel_reservation_pair_6.core.results.Result;
import com.tobeto.hotel_reservation_pair_6.core.results.SuccessResult;
import com.tobeto.hotel_reservation_pair_6.core.services.concretes.JwtService;
import com.tobeto.hotel_reservation_pair_6.core.utilities.exceptions.types.BusinessException;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.Guest;
import com.tobeto.hotel_reservation_pair_6.entities.enums.Role;
import com.tobeto.hotel_reservation_pair_6.repositories.GuestRepository;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.AuthenticationService;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.GuestService;
import com.tobeto.hotel_reservation_pair_6.services.dtos.authDtos.responses.AuthenticationResponse;
import com.tobeto.hotel_reservation_pair_6.services.dtos.guestDtos.requests.RegisterGuestRequest;
import com.tobeto.hotel_reservation_pair_6.services.dtos.guestDtos.requests.UpdateGuestRequest;
import com.tobeto.hotel_reservation_pair_6.services.mappers.GuestMapper;
import com.tobeto.hotel_reservation_pair_6.services.rules.abstracts.UserBusinessRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GuestServiceImpl implements GuestService{

    private final JwtService jwtService;
    private final GuestRepository guestRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserBusinessRuleService userBusinessRuleService;
    private final AuthenticationService authenticationService;

    @Override
    public AuthenticationResponse register(RegisterGuestRequest request) {
        userBusinessRuleService.checkIfEmailAlreadyExists(request.getEmail());

        userBusinessRuleService.checkIfPasswordsMatch(request.getPassword(), request.getPasswordConfirm());

        Guest guest = GuestMapper.INSTANCE.mapRegisterGuestRequestToGuest(request);
        guest.setPassword(passwordEncoder.encode(request.getPassword()));
        guest.setRole(Role.GUEST);

        var savedGuest = guestRepository.save(guest);
        var jwtToken = jwtService.generateToken(guest);
        var refreshToken = jwtService.generateRefreshToken(guest);

        authenticationService.saveUserToken(savedGuest, jwtToken);

        return AuthenticationResponse.builder().accessToken(jwtToken).refreshToken(refreshToken).build();
    }

    @Override
    public Result update(UpdateGuestRequest request) {
        Guest guest = GuestMapper.INSTANCE.mapUpdateGuestRequestToGuest(request);
        guestRepository.save(guest);
        return new SuccessResult("Guest updated.");
    }

    @Override
    public Guest findById(long id) {
        return guestRepository.findById(id).orElseThrow(() -> new BusinessException("Guest is not found."));
    }
}
