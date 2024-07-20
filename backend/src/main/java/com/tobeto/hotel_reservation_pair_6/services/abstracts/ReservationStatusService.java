package com.tobeto.hotel_reservation_pair_6.services.abstracts;

import com.tobeto.hotel_reservation_pair_6.entities.enums.ReservationStatus;

import java.util.List;

public interface ReservationStatusService {
    List<ReservationStatus> getAllReservationStatus();
}
