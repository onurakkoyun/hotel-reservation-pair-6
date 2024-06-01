package com.tobeto.hotel_reservation_pair_6.controllers;

import com.tobeto.hotel_reservation_pair_6.core.results.Result;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.HotelService;
import com.tobeto.hotel_reservation_pair_6.services.dtos.hotelDtos.requests.AddHotelRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hotels")
public class HotelsController {
    private final HotelService hotelService;
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    private Result add(@RequestBody @Valid AddHotelRequest request){
        return hotelService.add(request);
    }
}
