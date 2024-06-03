package com.tobeto.hotel_reservation_pair_6.services.abstracts;

import com.tobeto.hotel_reservation_pair_6.entities.concretes.Payment;
import com.tobeto.hotel_reservation_pair_6.services.dtos.reservationDtos.requests.CreateReservationRequest;

public interface PaymentService {
    Payment createPayment(CreateReservationRequest createReservationRequest, double amount);

    void save(Payment payment);

}
