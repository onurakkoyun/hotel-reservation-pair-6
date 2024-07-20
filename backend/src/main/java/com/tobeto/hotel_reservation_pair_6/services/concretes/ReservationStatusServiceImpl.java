package com.tobeto.hotel_reservation_pair_6.services.concretes;

import com.tobeto.hotel_reservation_pair_6.entities.enums.ReservationStatus;
import com.tobeto.hotel_reservation_pair_6.repositories.ReservationStatusRepository;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.ReservationStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationStatusServiceImpl implements ReservationStatusService {

    private final ReservationStatusRepository reservationStatusRepository;

    @Override
    public List<ReservationStatus> getAllReservationStatus() {
        return reservationStatusRepository.findAll();
    }
}
