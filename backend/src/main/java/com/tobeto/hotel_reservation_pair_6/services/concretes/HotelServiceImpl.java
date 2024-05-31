package com.tobeto.hotel_reservation_pair_6.services.concretes;

import com.tobeto.hotel_reservation_pair_6.core.results.Result;
import com.tobeto.hotel_reservation_pair_6.core.results.SuccessResult;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.Hotel;
import com.tobeto.hotel_reservation_pair_6.repositories.HotelRepository;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.HotelService;
import com.tobeto.hotel_reservation_pair_6.services.dtos.hotelDtos.requests.AddHotelRequest;
import com.tobeto.hotel_reservation_pair_6.services.mappers.HotelMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class HotelServiceImpl implements HotelService{

    private final HotelRepository hotelRepository;

    @Override
    public Result add(AddHotelRequest request) {
       Hotel hotel = HotelMapper.INSTANCE.mapAddHotelRequestToHotel(request);

       hotelRepository.save(hotel);
       return new SuccessResult("Hotel added.");
    }
}
