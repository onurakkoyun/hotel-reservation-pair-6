package com.tobeto.hotel_reservation_pair_6.services.mappers;

import com.tobeto.hotel_reservation_pair_6.entities.concretes.RoomImage;
import com.tobeto.hotel_reservation_pair_6.services.dtos.roomImageDtos.responses.GetRoomImageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoomImageMapper {
    RoomImageMapper INSTANCE =  Mappers.getMapper(RoomImageMapper.class);

    @Mapping(source = "room.id", target = "roomId")
    GetRoomImageResponse mapImageToGetRoomImageResponse(RoomImage roomImage);
}
