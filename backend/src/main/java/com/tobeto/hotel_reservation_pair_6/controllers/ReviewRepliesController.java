package com.tobeto.hotel_reservation_pair_6.controllers;

import com.tobeto.hotel_reservation_pair_6.core.results.Result;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.ReviewReplyService;
import com.tobeto.hotel_reservation_pair_6.services.dtos.reviewReplyDtos.requests.AddReviewReplyRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review-reply")
public class ReviewRepliesController {

    private final ReviewReplyService reviewReplyService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add")
    private Result add(@RequestBody @Valid final AddReviewReplyRequest request) {
        return reviewReplyService.add(request);
    }
}
