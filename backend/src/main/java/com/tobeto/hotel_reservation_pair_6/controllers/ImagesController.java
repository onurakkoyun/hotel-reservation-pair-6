package com.tobeto.hotel_reservation_pair_6.controllers;

import com.tobeto.hotel_reservation_pair_6.core.results.Result;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.HotelImageService;
import com.tobeto.hotel_reservation_pair_6.services.dtos.hotelImageDtos.responses.GetHotelImageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/images")
public class ImagesController {

    private final HotelImageService hotelImageService;

    @PostMapping("/upload-hotel")
    public Result uploadHotelImages(@RequestParam("id") int hotelId, @RequestParam("files") MultipartFile[] files) throws IOException {
        return hotelImageService.uploadHotelImages(files, hotelId);
    }

    @GetMapping("/get/{hotelId}")
    public List<GetHotelImageResponse> getImagesByHotelId(@PathVariable int hotelId) {
        return hotelImageService.getImagesByHotelId(hotelId);
    }
}
