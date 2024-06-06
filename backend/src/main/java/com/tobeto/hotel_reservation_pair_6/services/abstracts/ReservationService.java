package com.tobeto.hotel_reservation_pair_6.services.abstracts;

import com.tobeto.hotel_reservation_pair_6.core.results.Result;
import com.tobeto.hotel_reservation_pair_6.services.dtos.reservationDtos.requests.CreateReservationRequest;
import com.tobeto.hotel_reservation_pair_6.services.dtos.reservationDtos.responses.GetAllReservationsResponse;

import java.util.List;

public interface ReservationService {
    Result createReservation(CreateReservationRequest request);

    List<GetAllReservationsResponse> getAllHotelReservations(int hotelId);

    List<GetAllReservationsResponse> getAllGuestReservations(int guestId);

    Result updateReservationStatus(long id, boolean isApproved);

    Result cancelReservation(long id);

}
