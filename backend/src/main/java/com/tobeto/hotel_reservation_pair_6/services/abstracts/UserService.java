package com.tobeto.hotel_reservation_pair_6.services.abstracts;

import com.tobeto.hotel_reservation_pair_6.services.dtos.userDtos.responses.GetUserResponse;

public interface UserService {
    GetUserResponse getUserByEmail(String email);
}
