package com.tobeto.hotel_reservation_pair_6.services.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HotelFeatureMapper {
    HotelFeatureMapper INSTANCE =  Mappers.getMapper(HotelFeatureMapper.class);
}
