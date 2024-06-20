package com.tobeto.hotel_reservation_pair_6.services.concretes;

import com.tobeto.hotel_reservation_pair_6.core.results.Result;
import com.tobeto.hotel_reservation_pair_6.core.results.SuccessResult;
import com.tobeto.hotel_reservation_pair_6.core.utilities.exceptions.types.BusinessException;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.SupportTicket;
import com.tobeto.hotel_reservation_pair_6.entities.enums.SupportTicketStatus;
import com.tobeto.hotel_reservation_pair_6.repositories.SupportTicketRepository;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.SupportTicketService;
import com.tobeto.hotel_reservation_pair_6.services.dtos.supportTicketDtos.requests.CreateSupportTicketRequest;
import com.tobeto.hotel_reservation_pair_6.services.dtos.supportTicketDtos.responses.GetAllSupportTicketResponse;
import com.tobeto.hotel_reservation_pair_6.services.mappers.SupportTicketMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SupportTicketServiceImpl implements SupportTicketService {

    private final SupportTicketRepository supportTicketRepository;

    @Override
    public Result createSupportRequest(CreateSupportTicketRequest request) {

        SupportTicket supportTicket = SupportTicketMapper.INSTANCE
                .mapCreateSupportTicketRequestToSupportTicket(request);
        supportTicket.setCreationDate(LocalDateTime.now());
        supportTicket.setStatus(SupportTicketStatus.OPEN);

        supportTicketRepository.save(supportTicket);

        return new SuccessResult("Support ticket created.");
    }

    @Override
    public List<GetAllSupportTicketResponse> getSupportTicketsByGuestId(long guestId) {
        List<SupportTicket> supportTickets = supportTicketRepository.findAllByGuestId(guestId);

        List<GetAllSupportTicketResponse> responses = supportTickets.stream().map(supportTicket -> {
            GetAllSupportTicketResponse response = SupportTicketMapper.INSTANCE.mapSupportTicketToGetAllSupportTicketResponse(supportTicket);
            return response;
        }).collect(Collectors.toList());

        return responses;
    }

    //TODO:Ticket status'e göre (ör:Sadece OPEN ve IN_PROGRESS) getirme yapılabilir.
    @Override
    public List<GetAllSupportTicketResponse> getSupportTicketsByHotelId(int hotelId) {
        List<SupportTicket> supportTickets = supportTicketRepository
                .findAllByReservation_Room_Hotel_Id(hotelId);

        List<GetAllSupportTicketResponse> responses = supportTickets.stream().map(supportTicket -> {
            GetAllSupportTicketResponse response = SupportTicketMapper.INSTANCE
                    .mapSupportTicketToGetAllSupportTicketResponse(supportTicket);
            return response;
        }).collect(Collectors.toList());

        return responses;
    }

    @Override
    public SupportTicket findById(long id) {
        return supportTicketRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Support ticket not found."));
    }

    @Override
    public void save(SupportTicket supportTicket) {
        supportTicketRepository.save(supportTicket);
    }
}
