package com.tobeto.hotel_reservation_pair_6.services.concretes;

import com.tobeto.hotel_reservation_pair_6.core.utilities.exceptions.types.BusinessException;
import com.tobeto.hotel_reservation_pair_6.repositories.UserRepository;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.UserService;
import com.tobeto.hotel_reservation_pair_6.services.dtos.userDtos.responses.GetUserResponse;
import com.tobeto.hotel_reservation_pair_6.services.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public GetUserResponse getUserByEmail(String email) {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException("User not found."));

        GetUserResponse response = UserMapper.INSTANCE.mapUserToGetUserResponse(user);
        return response;
    }
}
