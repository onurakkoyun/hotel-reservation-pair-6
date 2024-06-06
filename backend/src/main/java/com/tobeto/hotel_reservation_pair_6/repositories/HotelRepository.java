package com.tobeto.hotel_reservation_pair_6.repositories;

import com.tobeto.hotel_reservation_pair_6.entities.concretes.Hotel;
import com.tobeto.hotel_reservation_pair_6.services.dtos.hotelDtos.responses.GetAllHotelsResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {
    Hotel findByRooms_Id(long id);

    @Query("SELECT h FROM Hotel h WHERE LOWER(h.hotelName) " +
            "LIKE LOWER(CONCAT('%', :searchText, '%')) " +
            "OR LOWER(h.city) LIKE LOWER(CONCAT('%', :searchText, '%')) " +
            "OR LOWER(h.province) LIKE LOWER(CONCAT('%', :searchText, '%')) " +
            "OR LOWER(h.country) LIKE LOWER(CONCAT('%', :searchText, '%'))")
    List<Hotel> searchHotels(@Param("searchText") String searchText);
}
