package com.tobeto.hotel_reservation_pair_6.services.abstracts;

import com.tobeto.hotel_reservation_pair_6.core.results.Result;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.Manager;
import com.tobeto.hotel_reservation_pair_6.services.dtos.authDtos.responses.AuthenticationResponse;
import com.tobeto.hotel_reservation_pair_6.services.dtos.managerDtos.requests.RegisterManagerRequest;
import com.tobeto.hotel_reservation_pair_6.services.dtos.managerDtos.requests.UpdateManagerRequest;

import java.io.IOException;

public interface ManagerService {
    Manager save(Manager manager);

    Result update(UpdateManagerRequest request) throws IOException;
}
