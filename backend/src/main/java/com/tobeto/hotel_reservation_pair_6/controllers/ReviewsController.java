package com.tobeto.hotel_reservation_pair_6.controllers;

import com.tobeto.hotel_reservation_pair_6.core.results.Result;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.ReviewService;
import com.tobeto.hotel_reservation_pair_6.services.dtos.reviewDtos.requests.AddReviewRequest;
import com.tobeto.hotel_reservation_pair_6.services.dtos.reviewDtos.responses.GetReviewResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewsController {
    private final ReviewService reviewService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add")
    private Result add(@RequestBody @Valid AddReviewRequest request) {
        return reviewService.add(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getByHotelId/{hotelId}")
    private List<GetReviewResponse> getReviewsByHotelId(@PathVariable int hotelId) {
        return reviewService.getReviwsByHotelId(hotelId);
    }

}
