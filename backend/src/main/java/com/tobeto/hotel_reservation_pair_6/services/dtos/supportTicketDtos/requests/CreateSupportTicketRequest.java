package com.tobeto.hotel_reservation_pair_6.services.dtos.supportTicketDtos.requests;

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
public class CreateSupportTicketRequest {

    @NotNull(message = "Description required.")
    private String description;

    private long guestId;

    private long reservationId;
}
