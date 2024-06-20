package com.tobeto.hotel_reservation_pair_6.services.mappers;

import com.tobeto.hotel_reservation_pair_6.entities.concretes.Reservation;
import com.tobeto.hotel_reservation_pair_6.services.dtos.reservationDtos.requests.CreateReservationRequest;
import com.tobeto.hotel_reservation_pair_6.services.dtos.reservationDtos.responses.GetAllReservationsResponse;
import com.tobeto.hotel_reservation_pair_6.services.dtos.reservationDtos.responses.GetReservationReportResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReservationMapper {
    ReservationMapper INSTANCE =  Mappers.getMapper(ReservationMapper.class);

    @Mapping(source = "roomId", target = "room.id")
    @Mapping(source = "guestId", target = "guest.id")
    Reservation mapCreateReservationRequestToReservation(CreateReservationRequest request);

    @Mapping(source = "room.hotel.id", target = "room.hotelId")
    @Mapping(source = "room.roomType.id", target = "room.roomTypeId")
    @Mapping(source = "room.roomType.roomTypeName", target = "room.roomTypeName")
    GetAllReservationsResponse mapReservationToGetAllReservationsResponse(Reservation reservation);


    @Mapping(source = "guest.firstName", target = "firstName")
    @Mapping(source = "guest.lastName", target = "lastName")
    @Mapping(source = "guest.email", target = "email")
    @Mapping(source = "guest.phoneNumber", target = "phoneNumber")
    @Mapping(source = "room.hotel.hotelName", target = "hotelName")
    GetReservationReportResponse mapReservationToGetReservationReportResponse(Reservation reservation);
}
