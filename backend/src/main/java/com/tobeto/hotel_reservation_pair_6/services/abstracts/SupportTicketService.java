package com.tobeto.hotel_reservation_pair_6.services.abstracts;

import com.tobeto.hotel_reservation_pair_6.core.results.Result;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.SupportTicket;
import com.tobeto.hotel_reservation_pair_6.services.dtos.supportTicketDtos.requests.CreateSupportTicketRequest;
import com.tobeto.hotel_reservation_pair_6.services.dtos.supportTicketDtos.responses.GetAllSupportTicketResponse;

import java.util.List;

public interface SupportTicketService {
    Result createSupportRequest(CreateSupportTicketRequest request);

    List<GetAllSupportTicketResponse> getSupportTicketsByGuestId(long guestId);

    List<GetAllSupportTicketResponse> getSupportTicketsByHotelId(int hotelId);

    SupportTicket findById(long id);

    void save(SupportTicket supportTicket);
}
