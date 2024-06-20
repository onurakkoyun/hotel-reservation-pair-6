package com.tobeto.hotel_reservation_pair_6.services.concretes;

import com.tobeto.hotel_reservation_pair_6.core.results.Result;
import com.tobeto.hotel_reservation_pair_6.core.results.SuccessResult;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.SupportTicket;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.SupportTicketReply;
import com.tobeto.hotel_reservation_pair_6.entities.enums.SupportTicketStatus;
import com.tobeto.hotel_reservation_pair_6.repositories.SupportTicketReplyRepository;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.SupportTicketReplyService;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.SupportTicketService;
import com.tobeto.hotel_reservation_pair_6.services.dtos.supportTicketReplyDtos.requests.CreateSupportTicketReplyRequest;
import com.tobeto.hotel_reservation_pair_6.services.mappers.SupportTicketReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class SupportTicketReplyServiceImpl implements SupportTicketReplyService {
    private final SupportTicketReplyRepository supportTicketReplyRepository;

    private final SupportTicketService supportTicketService;

    @Override
    public Result createSupportReplyRequest(CreateSupportTicketReplyRequest request) {
        SupportTicketReply supportTicketReply = SupportTicketReplyMapper.INSTANCE
                .mapCreateSupportTicketReplyRequestToSupportTicketReply(request);

        supportTicketReply.setCreationDate(LocalDateTime.now());

        SupportTicket supportTicket = supportTicketService
                .findById(request.getSupportTicketId());

        supportTicket.setStatus(SupportTicketStatus.CLOSED);

        supportTicketService.save(supportTicket);

        supportTicketReplyRepository.save(supportTicketReply);

        return new SuccessResult("Support ticket replied.");
    }
}
