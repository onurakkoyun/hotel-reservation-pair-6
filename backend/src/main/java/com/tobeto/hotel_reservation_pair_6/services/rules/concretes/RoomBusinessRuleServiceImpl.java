package com.tobeto.hotel_reservation_pair_6.services.rules.concretes;

import com.tobeto.hotel_reservation_pair_6.repositories.RoomRepository;
import com.tobeto.hotel_reservation_pair_6.services.rules.abstracts.RoomBusinessRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoomBusinessRuleServiceImpl implements RoomBusinessRuleService {
    private final RoomRepository roomRepository;

}
