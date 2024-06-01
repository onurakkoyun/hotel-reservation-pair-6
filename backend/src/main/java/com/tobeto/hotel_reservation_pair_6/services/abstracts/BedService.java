package com.tobeto.hotel_reservation_pair_6.services.abstracts;

import com.tobeto.hotel_reservation_pair_6.entities.concretes.Bed;

import java.util.Optional;

public interface BedService {
    Optional<Bed> findById(int id);
}
