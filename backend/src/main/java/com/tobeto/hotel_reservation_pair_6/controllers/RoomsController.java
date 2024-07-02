package com.tobeto.hotel_reservation_pair_6.controllers;

import com.tobeto.hotel_reservation_pair_6.core.results.Result;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.RoomService;
import com.tobeto.hotel_reservation_pair_6.services.dtos.roomDtos.requests.AddRoomRequest;
import com.tobeto.hotel_reservation_pair_6.services.dtos.roomDtos.requests.UpdateRoomRequest;
import com.tobeto.hotel_reservation_pair_6.services.dtos.roomDtos.responses.GetRoomResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rooms")
public class RoomsController {
    private final RoomService roomService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    private Result add(@RequestBody @Valid AddRoomRequest request) throws IOException {
        return roomService.add(request);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    private Result update(@RequestBody @Valid UpdateRoomRequest request){
        return roomService.update(request);
    }

    @GetMapping("/available-rooms")
    public List<GetRoomResponse> getAvailableRooms(@RequestParam("hotelId") int hotelId,
                                                   @RequestParam("guestCount") int guestCount,
                                                   @RequestParam("checkInDate") LocalDate checkInDate,
                                                   @RequestParam("checkOutDate") LocalDate checkOutDate) {
        return roomService.getAvailableRooms(hotelId, guestCount, checkInDate, checkOutDate);
    }
}
