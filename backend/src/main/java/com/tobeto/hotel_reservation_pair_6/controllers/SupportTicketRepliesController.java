package com.tobeto.hotel_reservation_pair_6.controllers;

import com.tobeto.hotel_reservation_pair_6.core.results.Result;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.SupportTicketReplyService;
import com.tobeto.hotel_reservation_pair_6.services.dtos.supportTicketReplyDtos.requests.CreateSupportTicketReplyRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/support-ticket-replies")
public class SupportTicketRepliesController {
    private final SupportTicketReplyService supportTicketReplyService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/reply")
    public Result createSupportTicketReply(@RequestBody @Valid CreateSupportTicketReplyRequest request) {
        return supportTicketReplyService.createSupportReplyRequest(request);
    }
}
