package com.tobeto.hotel_reservation_pair_6.services.mappers;

import com.tobeto.hotel_reservation_pair_6.entities.concretes.SupportTicket;
import com.tobeto.hotel_reservation_pair_6.services.dtos.supportTicketDtos.requests.CreateSupportTicketRequest;
import com.tobeto.hotel_reservation_pair_6.services.dtos.supportTicketDtos.responses.GetAllSupportTicketResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SupportTicketMapper {
    SupportTicketMapper INSTANCE =  Mappers.getMapper(SupportTicketMapper.class);

    @Mapping(source = "guestId", target = "guest.id")
    @Mapping(source = "reservationId", target = "reservation.id")
    SupportTicket mapCreateSupportTicketRequestToSupportTicket(CreateSupportTicketRequest request);

    @Mapping(source = "guest.id", target = "guestId")
    @Mapping(source = "reservation.id", target = "reservationId")
    GetAllSupportTicketResponse mapSupportTicketToGetAllSupportTicketResponse(SupportTicket supportTicket);
}
