package com.tobeto.hotel_reservation_pair_6.repositories;

import com.tobeto.hotel_reservation_pair_6.entities.concretes.HotelFeature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelFeatureRepository extends JpaRepository<HotelFeature, Integer> {
}
