package com.tobeto.hotel_reservation_pair_6.services.dtos.reservationDtos.requests;

import com.iyzipay.model.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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
    private Currency currency;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private long roomId;
    private long guestId;

}
