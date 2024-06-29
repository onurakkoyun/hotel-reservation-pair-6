package com.tobeto.hotel_reservation_pair_6.services.abstracts;

import com.tobeto.hotel_reservation_pair_6.core.results.Result;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.Hotel;
import com.tobeto.hotel_reservation_pair_6.services.dtos.hotelDtos.requests.AddHotelRequest;
import com.tobeto.hotel_reservation_pair_6.services.dtos.hotelDtos.responses.GetAllHotelsResponse;

import java.io.IOException;
import java.util.List;

public interface HotelService {
    Result add(AddHotelRequest request) throws IOException;

    Hotel findByRooms_Id(long id);

    Hotel findById(int hotelId);

    Hotel save(Hotel hotel);

    List<GetAllHotelsResponse> getAllHotels();

    List<GetAllHotelsResponse> searchHotels(String searchText, int guestCount);
}
