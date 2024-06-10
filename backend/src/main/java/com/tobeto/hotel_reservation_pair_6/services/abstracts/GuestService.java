package com.tobeto.hotel_reservation_pair_6.services.abstracts;

import com.tobeto.hotel_reservation_pair_6.core.results.Result;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.Guest;
import com.tobeto.hotel_reservation_pair_6.services.dtos.authDtos.responses.AuthenticationResponse;
import com.tobeto.hotel_reservation_pair_6.services.dtos.guestDtos.requests.RegisterGuestRequest;
import com.tobeto.hotel_reservation_pair_6.services.dtos.guestDtos.requests.UpdateGuestRequest;

import java.util.Optional;

public interface GuestService {
    AuthenticationResponse register(RegisterGuestRequest request);
    Result update(UpdateGuestRequest request);

    Guest findById(long id);

}
