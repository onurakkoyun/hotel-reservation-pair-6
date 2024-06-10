package com.tobeto.hotel_reservation_pair_6.repositories;

import com.tobeto.hotel_reservation_pair_6.entities.concretes.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findAllByHotel_IdAndPaymentDateBetween(int hotelId, LocalDate startDate, LocalDate endDate);
}
