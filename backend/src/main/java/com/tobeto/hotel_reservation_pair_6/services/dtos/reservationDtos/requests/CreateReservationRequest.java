package com.tobeto.hotel_reservation_pair_6.services.dtos.reservationDtos.requests;

import com.iyzipay.model.Currency;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "Card number required!")
    private String cardNumber;

    @NotNull(message = "Card holder name required!")
    private String cardHolderName;

    @NotNull(message = "Card expiration month required!")
    private String expirationMonth;

    @NotNull(message = "Card expiration year required!")
    private String expirationYear;

    @NotNull(message = "Card cvc required!")
    private String cvc;

    @NotNull(message = "Currency required!")
    private Currency currency;

    @Future(message = "The date must be in the future!")
    @NotNull(message = "Check-in date required!")
    private LocalDate checkInDate;

    @Future(message = "The date must be in the future!")
    @NotNull(message = "Check-out date required!")
    private LocalDate checkOutDate;

    private long roomId;

    private long guestId;

}
