package com.tobeto.hotel_reservation_pair_6.services.concretes;

import com.tobeto.hotel_reservation_pair_6.core.results.Result;
import com.tobeto.hotel_reservation_pair_6.core.results.SuccessResult;
import com.tobeto.hotel_reservation_pair_6.core.services.concretes.CloudinaryService;
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

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class GuestServiceImpl implements GuestService{

    private final GuestRepository guestRepository;
    private final UserBusinessRuleService userBusinessRuleService;

    @Override
    public Result update(UpdateGuestRequest request) throws IOException{
        Guest existingGuest = guestRepository.findById(request.getId())
                        .orElseThrow(() -> new BusinessException("Guest not found."));

        String profilePhoto = userBusinessRuleService.handleProfilePhotoChange
                (existingGuest.getId(), request.getProfilePhoto());

        existingGuest.setProfilePhotoUrl(profilePhoto);

        GuestMapper.INSTANCE.mapUpdateGuestRequestToGuest(request, existingGuest);

        guestRepository.save(existingGuest);
        return new SuccessResult("Guest updated.");
    }

    @Override
    public Guest save(Guest guest) {
        return guestRepository.save(guest);
    }

    @Override
    public Guest findById(long id) {
        return guestRepository.findById(id).orElseThrow(() -> new BusinessException("Guest is not found."));
    }
}
