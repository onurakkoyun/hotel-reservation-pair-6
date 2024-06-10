package com.tobeto.hotel_reservation_pair_6.controllers;

import com.tobeto.hotel_reservation_pair_6.core.results.Result;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.GuestService;
import com.tobeto.hotel_reservation_pair_6.services.dtos.authDtos.responses.AuthenticationResponse;
import com.tobeto.hotel_reservation_pair_6.services.dtos.guestDtos.requests.RegisterGuestRequest;
import com.tobeto.hotel_reservation_pair_6.services.dtos.guestDtos.requests.UpdateGuestRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/guests")
public class GuestsController {
    private final GuestService guestService;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    AuthenticationResponse register(@RequestBody @Valid RegisterGuestRequest request){
        return guestService.register(request);
    }
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/update")
    Result update(@RequestBody @Valid UpdateGuestRequest request) {
        return guestService.update(request);
    }
}
