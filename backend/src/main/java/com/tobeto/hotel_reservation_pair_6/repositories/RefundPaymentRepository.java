package com.tobeto.hotel_reservation_pair_6.repositories;

import com.tobeto.hotel_reservation_pair_6.entities.concretes.RefundPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RefundPaymentRepository extends JpaRepository<RefundPayment, Long> {
    List<RefundPayment> findAllByHotel_IdAndRefundDateBetween(int hotelId, LocalDate startDate, LocalDate endDate);
}
