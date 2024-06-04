package com.tobeto.hotel_reservation_pair_6.repositories;

import com.tobeto.hotel_reservation_pair_6.entities.concretes.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    /*@Query("SELECT r FROM Reservation r WHERE r.room.id = :roomId " +
            "AND r.status NOT IN ('CANCELED_BY_HOTEL', 'CANCELED_BY_GUEST') " +
            "AND (r.checkInDate < :checkOutDate AND r.checkOutDate > :checkInDate)")
    List<Reservation> findReservationsForRoomInDateRange(
            @Param("roomId") Long roomId,
            @Param("checkInDate") LocalDate checkInDate,
            @Param("checkOutDate") LocalDate checkOutDate);*/
}
