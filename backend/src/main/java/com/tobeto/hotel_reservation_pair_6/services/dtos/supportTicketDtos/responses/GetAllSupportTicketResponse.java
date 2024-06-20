package com.tobeto.hotel_reservation_pair_6.services.dtos.supportTicketDtos.responses;

import com.tobeto.hotel_reservation_pair_6.entities.enums.SupportTicketStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllSupportTicketResponse {

    private long id;

    private LocalDateTime creationDate;

    private String description;

    private SupportTicketStatus status;

    private long guestId;

    private long reservationId;
}
