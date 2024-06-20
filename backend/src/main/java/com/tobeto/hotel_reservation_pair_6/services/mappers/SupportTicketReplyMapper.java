package com.tobeto.hotel_reservation_pair_6.services.mappers;

import com.tobeto.hotel_reservation_pair_6.entities.concretes.SupportTicketReply;
import com.tobeto.hotel_reservation_pair_6.services.dtos.supportTicketReplyDtos.requests.CreateSupportTicketReplyRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SupportTicketReplyMapper {
    SupportTicketReplyMapper INSTANCE = Mappers.getMapper(SupportTicketReplyMapper.class);

    @Mapping(source = "supportTicketId", target = "supportTicket.id")
    @Mapping(source = "managerId", target = "manager.id")
    SupportTicketReply mapCreateSupportTicketReplyRequestToSupportTicketReply(CreateSupportTicketReplyRequest request);
}
