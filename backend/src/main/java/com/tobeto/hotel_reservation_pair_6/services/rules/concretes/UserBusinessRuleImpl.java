package com.tobeto.hotel_reservation_pair_6.services.rules.concretes;

import com.tobeto.hotel_reservation_pair_6.core.utilities.exceptions.types.BusinessException;
import com.tobeto.hotel_reservation_pair_6.repositories.UserRepository;
import com.tobeto.hotel_reservation_pair_6.services.rules.abstracts.UserBusinessRuleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserBusinessRuleImpl implements UserBusinessRuleService {
    private UserRepository userRepository;

    public void checkIfEmailAlreadyExists(String email) {
        this.userRepository.findByEmail(email).ifPresent(user -> {
            throw new BusinessException(user.getEmail() + " is not available.");
        });
    }

    @Override
    public void checkIfPasswordsMatch(String password, String passwordConfirm) {
        if(!password.equals(passwordConfirm)) {
            throw new BusinessException("Passwords does not match.");
        }
    }
}
