package com.tobeto.hotel_reservation_pair_6.services.concretes;

import com.tobeto.hotel_reservation_pair_6.core.results.Result;
import com.tobeto.hotel_reservation_pair_6.core.results.SuccessResult;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.Hotel;
import com.tobeto.hotel_reservation_pair_6.repositories.HotelRepository;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.HotelService;
import com.tobeto.hotel_reservation_pair_6.services.dtos.hotelDtos.requests.AddHotelRequest;
import com.tobeto.hotel_reservation_pair_6.services.dtos.hotelDtos.responses.GetAllHotelsResponse;
import com.tobeto.hotel_reservation_pair_6.services.mappers.HotelMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class HotelServiceImpl implements HotelService{

    //TODO: Konum veya Otel adına göre tüm otelleri listeleyecek methodu oluştur.

    private final HotelRepository hotelRepository;

    @Override
    public Result add(AddHotelRequest request) {
       Hotel hotel = HotelMapper.INSTANCE.mapAddHotelRequestToHotel(request);

       hotelRepository.save(hotel);
       return new SuccessResult("Hotel added.");
    }

    @Override
    public Hotel findByRooms_Id(long id) {
        return hotelRepository.findByRooms_Id(id);
    }

    @Override
    public Hotel findById(int hotelId) {
        return hotelRepository.findById(hotelId).orElseThrow();
    }

    @Override
    public List<GetAllHotelsResponse> searchHotels(String searchText) {

        List<Hotel> hotels = hotelRepository.searchHotels(searchText);

        List<GetAllHotelsResponse> responses = HotelMapper.INSTANCE.mapHotelToGetAllHotelsResponse(hotels);

        return responses;
    }
}
