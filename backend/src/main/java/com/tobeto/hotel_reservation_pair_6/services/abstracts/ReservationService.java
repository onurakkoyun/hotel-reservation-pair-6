package com.tobeto.hotel_reservation_pair_6.services.abstracts;

import com.tobeto.hotel_reservation_pair_6.core.results.Result;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.Reservation;
import com.tobeto.hotel_reservation_pair_6.services.dtos.reservationDtos.requests.CreateReservationRequest;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {
    Result createReservation(CreateReservationRequest request);

    /*List<Reservation> findReservationsForRoomInDateRange(Long roomId,
                                                          LocalDate checkInDate,
                                                          LocalDate checkOutDate);*/
}
