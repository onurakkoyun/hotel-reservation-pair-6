package com.tobeto.hotel_reservation_pair_6.services.abstracts;

import com.tobeto.hotel_reservation_pair_6.core.results.Result;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.Room;
import com.tobeto.hotel_reservation_pair_6.services.dtos.roomDtos.requests.AddRoomRequest;
import com.tobeto.hotel_reservation_pair_6.services.dtos.roomDtos.requests.UpdateRoomRequest;
import com.tobeto.hotel_reservation_pair_6.services.dtos.roomDtos.responses.GetRoomResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface RoomService {
    Result add(AddRoomRequest request) throws IOException;

    Result update(UpdateRoomRequest request);

    Room findById(long id);

    GetRoomResponse getRoomResponseById(long id);

    void save(Room room);

    List<GetRoomResponse> getAvailableRooms(int hotelId, int guestCount,
                                            LocalDate checkInDate, LocalDate checkOutDate);
}
