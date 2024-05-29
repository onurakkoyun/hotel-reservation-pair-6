package com.tobeto.hotel_reservation_pair_6.services.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PaymentMapper {
    PaymentMapper INSTANCE =  Mappers.getMapper(PaymentMapper.class);
}
