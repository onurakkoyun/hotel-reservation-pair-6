package com.tobeto.hotel_reservation_pair_6.services.dtos.supportTicketReplyDtos.requests;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateSupportTicketReplyRequest {

    @NotNull(message = "Ticket message required!")
    private String message;

    private long supportTicketId;

    private long managerId;
}
