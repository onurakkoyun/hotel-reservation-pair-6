package com.tobeto.hotel_reservation_pair_6.services.mappers;

import com.tobeto.hotel_reservation_pair_6.entities.concretes.Room;
import com.tobeto.hotel_reservation_pair_6.services.dtos.roomDtos.requests.AddRoomRequest;
import com.tobeto.hotel_reservation_pair_6.services.dtos.roomDtos.requests.UpdateRoomRequest;
import com.tobeto.hotel_reservation_pair_6.services.dtos.roomDtos.responses.GetRoomResponse;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {RoomBedMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface RoomMapper {
    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);

    @Mapping(source = "hotelId", target = "hotel.id")
    @Mapping(source = "roomTypeId", target = "roomType.id")
    Room mapAddRoomRequestToRoom(AddRoomRequest request);

    @Mapping(source = "hotelId", target = "hotel.id")
    @Mapping(source = "roomTypeId", target = "roomType.id")
    Room mapUpdateRoomRequestToRoom(UpdateRoomRequest request);

    @Mapping(source = "hotel.id", target = "hotelId")
    @Mapping(source = "roomType.id", target = "roomTypeId")
    @Mapping(source = "roomType.roomTypeName", target = "roomTypeName")
    GetRoomResponse mapRoomToGetRoomResponse(Room room);
}
