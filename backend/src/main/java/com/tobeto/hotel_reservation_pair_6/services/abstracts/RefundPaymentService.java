package com.tobeto.hotel_reservation_pair_6.services.abstracts;

import com.tobeto.hotel_reservation_pair_6.entities.concretes.RefundPayment;

public interface RefundPaymentService {
    RefundPayment createRefund(long paymentId, double amount);
}
