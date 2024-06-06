package com.tobeto.hotel_reservation_pair_6.services.dtos.reservationDtos.responses;

import com.iyzipay.model.Currency;
import com.tobeto.hotel_reservation_pair_6.entities.enums.ReservationStatus;
import com.tobeto.hotel_reservation_pair_6.services.dtos.guestDtos.responses.GetGuestResponse;
import com.tobeto.hotel_reservation_pair_6.services.dtos.roomDtos.responses.GetRoomResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllReservationsResponse {

    private LocalDate createdDate;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private double amount;

    private Currency currency;

    private ReservationStatus status;

    private GetGuestResponse guest;

    private GetRoomResponse room;

}
