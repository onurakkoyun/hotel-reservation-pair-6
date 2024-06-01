package com.tobeto.hotel_reservation_pair_6.services.mappers;

import com.tobeto.hotel_reservation_pair_6.entities.concretes.RoomBed;
import com.tobeto.hotel_reservation_pair_6.services.dtos.roomBedDtos.requests.AssignRoomBedRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoomBedMapper {
    RoomBedMapper INSTANCE =  Mappers.getMapper(RoomBedMapper.class);
}
