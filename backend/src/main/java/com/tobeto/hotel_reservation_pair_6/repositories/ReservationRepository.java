package com.tobeto.hotel_reservation_pair_6.repositories;


import com.tobeto.hotel_reservation_pair_6.entities.concretes.Reservation;
import com.tobeto.hotel_reservation_pair_6.services.dtos.reservationDtos.responses.GetReservationReportResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByRoom_Hotel_Id(int hotelId);

    List<Reservation> findByGuest_Id(long guestId);

    @Query("SELECT r FROM Reservation r WHERE r.room.hotel.id = :hotelId AND r.checkInDate BETWEEN :startDate AND :endDate")
    List<Reservation> findReservationsByHotelAndDates(@Param("hotelId") Integer hotelId,
                                                      @Param("startDate") LocalDate startDate,
                                                      @Param("endDate") LocalDate endDate);
}