package com.tobeto.hotel_reservation_pair_6.services.mappers;

import com.tobeto.hotel_reservation_pair_6.entities.concretes.Room;
import com.tobeto.hotel_reservation_pair_6.services.dtos.roomDtos.requests.AddRoomRequest;
import com.tobeto.hotel_reservation_pair_6.services.dtos.roomDtos.requests.UpdateRoomRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoomMapper {
    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);

    @Mapping(source = "hotelId", target = "hotel.id")
    @Mapping(source = "roomTypeId", target = "roomType.id")
    Room mapAddRoomRequestToRoom(AddRoomRequest request);

    @Mapping(source = "hotelId", target = "hotel.id")
    @Mapping(source = "roomTypeId", target = "roomType.id")
    Room mapUpdateRoomRequestToRoom(UpdateRoomRequest request);
}
