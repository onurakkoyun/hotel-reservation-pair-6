package com.tobeto.hotel_reservation_pair_6.services.dtos.reservationDtos.responses;

import com.iyzipay.model.Currency;
import com.tobeto.hotel_reservation_pair_6.entities.enums.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetReservationReportResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String hotelName;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private double amount;
    private String status;
    private String currency;
}
