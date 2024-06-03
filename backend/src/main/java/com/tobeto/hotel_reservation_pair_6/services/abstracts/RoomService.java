package com.tobeto.hotel_reservation_pair_6.services.abstracts;

import com.tobeto.hotel_reservation_pair_6.core.results.Result;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.Room;
import com.tobeto.hotel_reservation_pair_6.services.dtos.roomDtos.requests.AddRoomRequest;
import com.tobeto.hotel_reservation_pair_6.services.dtos.roomDtos.requests.UpdateRoomRequest;
import com.tobeto.hotel_reservation_pair_6.services.dtos.roomDtos.responses.GetAvailableRoomResponse;

import java.time.LocalDate;
import java.util.List;

public interface RoomService {
    Result add(AddRoomRequest request);

    Result update(UpdateRoomRequest request);

    Room findById(long id);

    void save(Room room);

    List<GetAvailableRoomResponse> getAvailableRooms(int hotelId, LocalDate checkInDate, LocalDate checkOutDate);
}
