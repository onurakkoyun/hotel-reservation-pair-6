package com.tobeto.hotel_reservation_pair_6.services.mappers;

import com.tobeto.hotel_reservation_pair_6.entities.concretes.Image;
import com.tobeto.hotel_reservation_pair_6.services.dtos.imageDtos.responses.GetHotelImageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ImageMapper {
    ImageMapper INSTANCE =  Mappers.getMapper(ImageMapper.class);

    @Mapping(source = "hotel.id", target = "hotelId")
    GetHotelImageResponse mapImageToGetHotelImageResponse(Image image);
}
