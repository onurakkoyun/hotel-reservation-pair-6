package com.tobeto.hotel_reservation_pair_6.services.mappers;

import com.tobeto.hotel_reservation_pair_6.entities.concretes.Hotel;
import com.tobeto.hotel_reservation_pair_6.services.dtos.hotelDtos.requests.AddHotelRequest;
import com.tobeto.hotel_reservation_pair_6.services.dtos.hotelDtos.responses.GetAllHotelsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HotelMapper {
    HotelMapper INSTANCE =  Mappers.getMapper(HotelMapper.class);

    @Mapping(source = "managerId", target = "manager.id")
    Hotel mapAddHotelRequestToHotel(AddHotelRequest request);

    @Mapping(source = "manager.id", target = "managerId")
    GetAllHotelsResponse mapHotelToGetAllHotelsResponse(Hotel hotel);
}
