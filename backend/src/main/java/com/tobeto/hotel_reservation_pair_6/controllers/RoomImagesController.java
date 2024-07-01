package com.tobeto.hotel_reservation_pair_6.controllers;

import com.tobeto.hotel_reservation_pair_6.services.abstracts.RoomImageService;
import com.tobeto.hotel_reservation_pair_6.services.dtos.roomImageDtos.responses.GetRoomImageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/room-images")
public class RoomImagesController {

    private final RoomImageService roomImageService;

    @GetMapping("/room/{roomId}")
    public List<GetRoomImageResponse> getImagesByRoomId(@PathVariable long roomId) {
        return roomImageService.getImagesByRoomId(roomId);
    }
}
