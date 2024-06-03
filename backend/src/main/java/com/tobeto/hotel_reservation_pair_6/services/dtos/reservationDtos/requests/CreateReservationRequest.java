package com.tobeto.hotel_reservation_pair_6.services.dtos.reservationDtos.requests;

import com.tobeto.hotel_reservation_pair_6.entities.enums.ReservationStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateReservationRequest {
    private String cardNumber;
    private String cardHolderName;
    private String expirationMonth;
    private String expirationYear;
    private String cvc;
    private String currency;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private long roomId;
    private long guestId;

}
