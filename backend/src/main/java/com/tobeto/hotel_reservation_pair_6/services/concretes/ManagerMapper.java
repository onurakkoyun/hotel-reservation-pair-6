package com.tobeto.hotel_reservation_pair_6.services.concretes;

import com.tobeto.hotel_reservation_pair_6.entities.concretes.Manager;
import com.tobeto.hotel_reservation_pair_6.services.dtos.managerDtos.requests.RegisterManagerRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ManagerMapper {
    ManagerMapper INSTANCE =  Mappers.getMapper(ManagerMapper.class);

    Manager mapRegisterManagerRequestToManager(RegisterManagerRequest request);
}