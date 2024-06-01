package com.tobeto.hotel_reservation_pair_6.controllers;

import com.tobeto.hotel_reservation_pair_6.core.results.Result;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.RoomService;
import com.tobeto.hotel_reservation_pair_6.services.dtos.hotelDtos.requests.AddHotelRequest;
import com.tobeto.hotel_reservation_pair_6.services.dtos.roomDtos.requests.AddRoomRequest;
import com.tobeto.hotel_reservation_pair_6.services.dtos.roomDtos.requests.UpdateRoomRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rooms")
public class RoomsController {
    private final RoomService roomService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    private Result add(@RequestBody @Valid AddRoomRequest request){
        return roomService.add(request);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    private Result update(@RequestBody @Valid UpdateRoomRequest request){
        return roomService.update(request);
    }
}
