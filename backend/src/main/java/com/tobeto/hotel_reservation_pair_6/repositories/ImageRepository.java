package com.tobeto.hotel_reservation_pair_6.repositories;

import com.tobeto.hotel_reservation_pair_6.entities.concretes.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByHotelId(int hotelId);
}
