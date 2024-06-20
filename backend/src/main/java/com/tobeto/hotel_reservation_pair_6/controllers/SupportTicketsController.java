package com.tobeto.hotel_reservation_pair_6.controllers;

import com.tobeto.hotel_reservation_pair_6.core.results.Result;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.SupportTicketService;
import com.tobeto.hotel_reservation_pair_6.services.dtos.supportTicketDtos.requests.CreateSupportTicketRequest;
import com.tobeto.hotel_reservation_pair_6.services.dtos.supportTicketDtos.responses.GetAllSupportTicketResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/support-tickets")
public class SupportTicketsController {

    private final SupportTicketService supportTicketService;

    @PostMapping("/create")
    public Result createSupportRequest(@RequestBody @Valid CreateSupportTicketRequest request) {
        return supportTicketService.createSupportRequest(request);
    }

    @GetMapping("/guest/{guestId}")
    public List<GetAllSupportTicketResponse> getSupportRequestsByGuestId(@PathVariable long guestId) {
        return supportTicketService.getSupportTicketsByGuestId(guestId);
    }

    @GetMapping("/hotel/{hotelId}")
    public List<GetAllSupportTicketResponse> getSupportRequestsByHotelId(@PathVariable int hotelId) {
        return supportTicketService.getSupportTicketsByHotelId(hotelId);
    }
}
