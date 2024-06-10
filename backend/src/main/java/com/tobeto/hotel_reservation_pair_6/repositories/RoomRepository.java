package com.tobeto.hotel_reservation_pair_6.repositories;

import com.tobeto.hotel_reservation_pair_6.entities.concretes.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("SELECT r FROM Room r WHERE r.hotel.id = :hotelId AND r.quantity > " +
            "(SELECT COUNT(res) FROM Reservation res WHERE res.room.id = r.id AND " +
            "((res.checkInDate <= :endDate AND res.checkOutDate >= :startDate) OR " +
            "(res.checkInDate >= :startDate AND res.checkInDate <= :endDate) OR " +
            "(res.checkOutDate >= :startDate AND res.checkOutDate <= :endDate)) AND " +
            "(res.status = 'APPROVED_BY_HOTEL' OR res.status = 'PENDING_APPROVAL_BY_HOTEL'))")
    List<Room> findAvailableRoomsByHotelAndDates(@Param("hotelId") int hotelId,
                                                 @Param("startDate") LocalDate startDate,
                                                 @Param("endDate") LocalDate endDate);

    List<Room> findByHotelId(Long hotelId);
}
