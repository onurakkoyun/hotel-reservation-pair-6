package com.tobeto.hotel_reservation_pair_6.services.rules.abstracts;

public interface UserBusinessRuleService {
    void checkIfEmailAlreadyExists(String email);

    void checkIfPasswordsMatch(String password, String passwordConfirm);
}
