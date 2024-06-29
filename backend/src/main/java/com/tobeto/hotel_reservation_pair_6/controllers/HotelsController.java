package com.tobeto.hotel_reservation_pair_6.controllers;

import com.tobeto.hotel_reservation_pair_6.core.results.Result;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.HotelService;
import com.tobeto.hotel_reservation_pair_6.services.dtos.hotelDtos.requests.AddHotelRequest;
import com.tobeto.hotel_reservation_pair_6.services.dtos.hotelDtos.responses.GetAllHotelsResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hotels")
public class HotelsController {
    private final HotelService hotelService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    private Result add(@ModelAttribute @Valid AddHotelRequest request) throws IOException {
        return hotelService.add(request);
    }

    @GetMapping("/get-all")
    private List<GetAllHotelsResponse> getAllHotels(){
        return hotelService.getAllHotels();
    }

    @GetMapping("/search")
    private List<GetAllHotelsResponse> searchHotels(@RequestParam(required = false) String query,
                                                    @RequestParam(required = false) int guestCount){
        return hotelService.searchHotels(query, guestCount);
    }
}
