package com.tobeto.hotel_reservation_pair_6.controllers;

import com.tobeto.hotel_reservation_pair_6.services.abstracts.HotelImageService;
import com.tobeto.hotel_reservation_pair_6.services.dtos.hotelImageDtos.responses.GetHotelImageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hotel-images")
public class HotelImagesController {

    private final HotelImageService hotelImageService;

    @GetMapping("/get/{hotelId}")
    public List<GetHotelImageResponse> getImagesByHotelId(@PathVariable int hotelId) {
        return hotelImageService.getImagesByHotelId(hotelId);
    }
}
