package com.tobeto.hotel_reservation_pair_6.services.concretes;

import com.tobeto.hotel_reservation_pair_6.core.utilities.exceptions.types.BusinessException;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.Bed;
import com.tobeto.hotel_reservation_pair_6.repositories.BedRepository;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.BedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BedServiceImpl implements BedService{
    private final BedRepository bedRepository;


    @Override
    public Optional<Bed> findById(int id) {
        return Optional.ofNullable(bedRepository.findById(id).orElseThrow(() -> new BusinessException("Bed not found.")));
    }
}
