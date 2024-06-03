package com.tobeto.hotel_reservation_pair_6.repositories;

import com.tobeto.hotel_reservation_pair_6.entities.concretes.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query("SELECT r FROM Room r WHERE r.hotel.id = :hotelId AND r.bookedRoomQuantity < r.quantity")
    List<Room> findAvailableRoomsByHotelId(@Param("hotelId") int hotelId);
}
