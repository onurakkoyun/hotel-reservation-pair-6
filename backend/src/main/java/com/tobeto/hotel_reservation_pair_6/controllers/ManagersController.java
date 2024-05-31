package com.tobeto.hotel_reservation_pair_6.controllers;

import com.tobeto.hotel_reservation_pair_6.services.abstracts.ManagerService;
import com.tobeto.hotel_reservation_pair_6.services.dtos.authDtos.responses.AuthenticationResponse;
import com.tobeto.hotel_reservation_pair_6.services.dtos.managerDtos.requests.RegisterManagerRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/managers")
public class ManagersController {
    private final ManagerService managerService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    AuthenticationResponse register(@RequestBody @Valid RegisterManagerRequest request){
        return managerService.register(request);
    }


}
