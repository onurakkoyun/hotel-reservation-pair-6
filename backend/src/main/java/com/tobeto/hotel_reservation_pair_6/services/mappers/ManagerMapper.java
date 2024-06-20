package com.tobeto.hotel_reservation_pair_6.services.mappers;

import com.tobeto.hotel_reservation_pair_6.entities.concretes.Manager;
import com.tobeto.hotel_reservation_pair_6.services.dtos.managerDtos.requests.RegisterManagerRequest;
import com.tobeto.hotel_reservation_pair_6.services.dtos.managerDtos.requests.UpdateManagerRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ManagerMapper {
    ManagerMapper INSTANCE =  Mappers.getMapper(ManagerMapper.class);

    Manager mapRegisterManagerRequestToManager(RegisterManagerRequest request);

    void mapUpdateManagerRequestToManager(UpdateManagerRequest request, @MappingTarget Manager manager);
}
