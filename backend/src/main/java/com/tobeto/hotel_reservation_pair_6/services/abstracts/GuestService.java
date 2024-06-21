package com.tobeto.hotel_reservation_pair_6.services.abstracts;

import com.tobeto.hotel_reservation_pair_6.core.results.Result;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.Guest;
import com.tobeto.hotel_reservation_pair_6.services.dtos.guestDtos.requests.UpdateGuestRequest;

import java.io.IOException;

public interface GuestService {

    Result update(UpdateGuestRequest request) throws IOException;

    Guest save(Guest guest);

    Guest findById(long id);

}
