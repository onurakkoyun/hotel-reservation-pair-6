package com.tobeto.hotel_reservation_pair_6.controllers;

import com.tobeto.hotel_reservation_pair_6.core.results.DataResult;
import com.tobeto.hotel_reservation_pair_6.core.results.Result;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.Image;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.ImageService;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.HotelService;
import com.tobeto.hotel_reservation_pair_6.services.dtos.imageDtos.responses.GetHotelImageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/images")
public class ImagesController {

    private final ImageService imageService;

    @PostMapping("/upload-hotel")
    public Result uploadHotelImages(@RequestParam("id") int hotelId, @RequestParam("files") MultipartFile[] files) throws IOException {
        return imageService.uploadHotelImages(files, hotelId);
    }

    @GetMapping("/get/{hotelId}")
    public List<GetHotelImageResponse> getImagesByHotelId(@PathVariable int hotelId) {
        return imageService.getImagesByHotelId(hotelId);
    }
}
