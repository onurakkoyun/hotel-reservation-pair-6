package com.tobeto.hotel_reservation_pair_6.services.dtos.roomImageDtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetRoomImageResponse {
    private long id;

    private String url;

    private long roomId;
}
