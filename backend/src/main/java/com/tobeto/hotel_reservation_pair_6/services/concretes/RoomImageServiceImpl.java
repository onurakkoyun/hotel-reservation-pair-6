package com.tobeto.hotel_reservation_pair_6.services.concretes;

import com.tobeto.hotel_reservation_pair_6.entities.concretes.RoomImage;
import com.tobeto.hotel_reservation_pair_6.repositories.RoomImageRepository;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.RoomImageService;
import com.tobeto.hotel_reservation_pair_6.services.dtos.roomImageDtos.responses.GetRoomImageResponse;
import com.tobeto.hotel_reservation_pair_6.services.mappers.RoomImageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RoomImageServiceImpl implements RoomImageService {

    private final RoomImageRepository roomImageRepository;

    @Override
    public List<GetRoomImageResponse> getImagesByRoomId(long roomId) {
        List<RoomImage> roomImages = roomImageRepository.findByRoom_Id(roomId);

        return roomImages.stream().map(image -> {
            GetRoomImageResponse response = RoomImageMapper.INSTANCE.mapImageToGetRoomImageResponse(image);
            return response;
        }).collect(Collectors.toList());
    }

    @Override
    public void saveAll(List<RoomImage> roomImages) {
        roomImageRepository.saveAll(roomImages);
    }
}
