package com.tobeto.hotel_reservation_pair_6.services.concretes;

import com.tobeto.hotel_reservation_pair_6.core.results.Result;
import com.tobeto.hotel_reservation_pair_6.core.results.SuccessResult;
import com.tobeto.hotel_reservation_pair_6.core.utilities.exceptions.types.BusinessException;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.Room;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.RoomBed;
import com.tobeto.hotel_reservation_pair_6.repositories.RoomRepository;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.BedService;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.RoomService;
import com.tobeto.hotel_reservation_pair_6.services.dtos.roomDtos.requests.AddRoomRequest;
import com.tobeto.hotel_reservation_pair_6.services.dtos.roomDtos.requests.UpdateRoomRequest;
import com.tobeto.hotel_reservation_pair_6.services.mappers.RoomMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RoomServiceImpl implements RoomService{
    private final RoomRepository roomRepository;

    private final BedService bedService;

    @Transactional
    @Override
    public Result add(AddRoomRequest request) {

        Room room = RoomMapper.INSTANCE.mapAddRoomRequestToRoom(request);

        if (request.getRoomBeds() != null) {
            List<RoomBed> roomBeds = request.getRoomBeds().stream().map(roomBedDTO -> {
                RoomBed roomBed = new RoomBed();
                roomBed.setBed(bedService.findById(roomBedDTO.getBedId()).orElseThrow(() -> new RuntimeException("Bed not found")));
                roomBed.setQuantity(roomBedDTO.getQuantity());
                roomBed.setRoom(room);
                return roomBed;
            }).collect(Collectors.toList());
            room.setRoomBeds(roomBeds);
        }

        roomRepository.save(room);

        return new SuccessResult("Room added.");
    }

    @Override
    public Result update(UpdateRoomRequest request){
        Room existingRoom = roomRepository.findById(request.getId())
                .orElseThrow(() -> new BusinessException("Room not found."));

        existingRoom = RoomMapper.INSTANCE.mapUpdateRoomRequestToRoom(request);

        if (request.getRoomBeds() != null) {
            Room finalExistingRoom = existingRoom;
            List<RoomBed> roomBeds = request.getRoomBeds().stream().map(roomBedDTO -> {
                RoomBed roomBed = new RoomBed();
                roomBed.setBed(bedService.findById(roomBedDTO.getBedId()).orElseThrow(() -> new RuntimeException("Bed not found")));
                roomBed.setQuantity(roomBedDTO.getQuantity());
                roomBed.setRoom(finalExistingRoom);
                return roomBed;
            }).collect(Collectors.toList());
            existingRoom.setRoomBeds(roomBeds);
        }

        roomRepository.save(existingRoom);

        return new SuccessResult("Room updated.");
    }
}
