package com.tobeto.hotel_reservation_pair_6.repositories;

import com.tobeto.hotel_reservation_pair_6.entities.concretes.RefundPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefundPaymentRepository extends JpaRepository<RefundPayment, Long> {
}
