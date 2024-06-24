package com.tobeto.hotel_reservation_pair_6.controllers;

import com.tobeto.hotel_reservation_pair_6.core.utilities.configurations.logging.LogActivity;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.User;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UsersController {
    private final UserService userService;
    @GetMapping("/user/{id}")
    @LogActivity("Kullanıcı detayları getiriliyor")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        // Kullanıcı detaylarını getirme kodunuz
        return ResponseEntity.ok(new User());
    }
}
