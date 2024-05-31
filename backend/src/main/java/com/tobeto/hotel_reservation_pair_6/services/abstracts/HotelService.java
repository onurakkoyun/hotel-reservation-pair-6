package com.tobeto.hotel_reservation_pair_6.services.abstracts;

import com.tobeto.hotel_reservation_pair_6.core.results.Result;
import com.tobeto.hotel_reservation_pair_6.services.dtos.hotelDtos.requests.AddHotelRequest;

public interface HotelService {
    Result add(AddHotelRequest request);
}
