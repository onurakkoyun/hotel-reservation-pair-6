package com.tobeto.hotel_reservation_pair_6.services.abstracts;

import com.tobeto.hotel_reservation_pair_6.entities.concretes.RoomImage;
import com.tobeto.hotel_reservation_pair_6.services.dtos.roomImageDtos.responses.GetRoomImageResponse;

import java.util.List;

public interface RoomImageService {
    List<GetRoomImageResponse> getImagesByRoomId(long roomId);

    void saveAll(List<RoomImage> roomImages);
}
