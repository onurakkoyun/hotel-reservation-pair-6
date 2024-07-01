package com.tobeto.hotel_reservation_pair_6.services.concretes;

import com.tobeto.hotel_reservation_pair_6.core.results.Result;
import com.tobeto.hotel_reservation_pair_6.core.results.SuccessResult;
import com.tobeto.hotel_reservation_pair_6.core.services.concretes.CloudinaryService;
import com.tobeto.hotel_reservation_pair_6.core.utilities.exceptions.types.BusinessException;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.*;
import com.tobeto.hotel_reservation_pair_6.repositories.RoomRepository;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.BedService;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.RoomImageService;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.RoomService;
import com.tobeto.hotel_reservation_pair_6.services.dtos.roomDtos.requests.AddRoomRequest;
import com.tobeto.hotel_reservation_pair_6.services.dtos.roomDtos.requests.UpdateRoomRequest;
import com.tobeto.hotel_reservation_pair_6.services.dtos.roomDtos.responses.GetRoomResponse;
import com.tobeto.hotel_reservation_pair_6.services.mappers.RoomMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RoomServiceImpl implements RoomService{
    private final RoomRepository roomRepository;
    private final RoomImageService roomImageService;
    private final BedService bedService;
    private final CloudinaryService cloudinaryService;

    @Transactional
    @Override
    public Result add(AddRoomRequest request) throws IOException {

        Room room = RoomMapper.INSTANCE.mapAddRoomRequestToRoom(request);

        List<RoomImage> roomImages = new ArrayList<>();

        for (MultipartFile file : request.getPhotos()) {
            String url = cloudinaryService.uploadFile(file);

            RoomImage roomImage = new RoomImage();
            roomImage.setUrl(url);
            roomImage.setRoom(room);

            roomImages.add(roomImage);
        }

        room.setRoomImages(roomImages);

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

        roomImageService.saveAll(roomImages);

        return new SuccessResult("Room added.");
    }

    @Override
    public Result update(UpdateRoomRequest request){
        var existingRoom = roomRepository.findById(request.getId())
                .orElseThrow(() -> new BusinessException("Room not found."));

        existingRoom = RoomMapper.INSTANCE.mapUpdateRoomRequestToRoom(request);

        if (request.getRoomBeds() != null) {
            Room finalExistingRoom = existingRoom;
            List<RoomBed> roomBeds = request.getRoomBeds().stream().map(roomBedDTO -> {
                RoomBed roomBed = new RoomBed();
                Bed bed = bedService.findById(roomBedDTO.getBedId())
                        .orElseThrow(() -> new BusinessException("Bed not found."));
                roomBed.setBed(bed);
                roomBed.setQuantity(roomBedDTO.getQuantity());
                roomBed.setRoom(finalExistingRoom);
                return roomBed;
            }).collect(Collectors.toList());
            existingRoom.setRoomBeds(roomBeds);
        }

        roomRepository.save(existingRoom);

        return new SuccessResult("Room updated.");
    }

    @Override
    public Room findById(long id) {
        return roomRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(Room room) {
        roomRepository.save(room);
    }

    @Override
    public List<GetRoomResponse> getAvailableRooms(int hotelId, int guestCount, LocalDate checkInDate, LocalDate checkOutDate) {
        List<Room> allRooms = roomRepository.searchAvailableRoomsByHotelId(hotelId, guestCount, checkInDate, checkOutDate);

        List<GetRoomResponse> responses = allRooms.stream().map(room -> {
            GetRoomResponse availableRooms = RoomMapper.INSTANCE.mapRoomToGetAvailableRoomsResponse(room);
            return availableRooms;
        }).collect(Collectors.toList());

        return responses;
    }
}
