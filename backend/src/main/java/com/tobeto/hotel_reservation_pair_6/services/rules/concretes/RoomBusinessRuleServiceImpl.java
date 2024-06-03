package com.tobeto.hotel_reservation_pair_6.services.rules.concretes;

import com.tobeto.hotel_reservation_pair_6.core.utilities.exceptions.types.BusinessException;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.Room;
import com.tobeto.hotel_reservation_pair_6.repositories.RoomRepository;
import com.tobeto.hotel_reservation_pair_6.services.rules.abstracts.RoomBusinessRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoomBusinessRuleServiceImpl implements RoomBusinessRuleService {
    private final RoomRepository roomRepository;


    @Override
    public void checkRoomAvailability(Room room) {
        int roomQuantity = room.getQuantity();
        int bookedQuantity = room.getBookedRoomQuantity();

        if (roomQuantity == bookedQuantity){
            throw new BusinessException("The requested room is not available.");
        }
    }
}
