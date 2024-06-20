package com.tobeto.hotel_reservation_pair_6.services.mappers;

import com.tobeto.hotel_reservation_pair_6.entities.concretes.HotelImage;
import com.tobeto.hotel_reservation_pair_6.services.dtos.hotelImageDtos.responses.GetHotelImageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HotelImageMapper {
    HotelImageMapper INSTANCE =  Mappers.getMapper(HotelImageMapper.class);

    @Mapping(source = "hotel.id", target = "hotelId")
    GetHotelImageResponse mapImageToGetHotelImageResponse(HotelImage hotelImage);
}
