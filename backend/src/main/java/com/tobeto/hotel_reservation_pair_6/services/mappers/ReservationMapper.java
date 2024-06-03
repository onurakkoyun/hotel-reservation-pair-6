package com.tobeto.hotel_reservation_pair_6.services.mappers;

import com.tobeto.hotel_reservation_pair_6.entities.concretes.Reservation;
import com.tobeto.hotel_reservation_pair_6.services.dtos.reservationDtos.requests.CreateReservationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReservationMapper {
    ReservationMapper INSTANCE =  Mappers.getMapper(ReservationMapper.class);

    @Mapping(source = "roomId", target = "room.id")
    @Mapping(source = "guestId", target = "guest.id")
    Reservation mapCreateReservationRequestToReservation(CreateReservationRequest request);
}
