package com.tobeto.hotel_reservation_pair_6.services.abstracts;

import com.tobeto.hotel_reservation_pair_6.core.results.Result;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.Hotel;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.Room;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.RoomType;
import com.tobeto.hotel_reservation_pair_6.services.dtos.hotelDtos.requests.AddHotelRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface HotelService {
    Result add(AddHotelRequest request);

    Hotel findByRooms_Id(long id);
}
