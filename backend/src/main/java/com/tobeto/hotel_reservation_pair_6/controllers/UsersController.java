package com.tobeto.hotel_reservation_pair_6.controllers;

import com.tobeto.hotel_reservation_pair_6.services.abstracts.UserService;
import com.tobeto.hotel_reservation_pair_6.services.dtos.userDtos.responses.GetUserResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UsersController {
    private final UserService userService;

    @GetMapping("/get-by-email")
    public GetUserResponse getByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email);
    }
}
