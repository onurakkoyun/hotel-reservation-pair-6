package com.tobeto.hotel_reservation_pair_6.controllers;

import com.tobeto.hotel_reservation_pair_6.core.results.Result;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.ReservationService;
import com.tobeto.hotel_reservation_pair_6.services.dtos.reservationDtos.requests.CreateReservationRequest;
import com.tobeto.hotel_reservation_pair_6.services.dtos.reservationDtos.responses.GetAllReservationsResponse;
import com.tobeto.hotel_reservation_pair_6.services.dtos.reservationDtos.responses.GetReservationReportResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservations")
public class ReservationsController {
    private final ReservationService reservationService;

    @PostMapping("/create")
    private Result createReservation(@RequestBody @Valid CreateReservationRequest request){
        return reservationService.createReservation(request);
    }

    @GetMapping("/getAllByHotelId")
    private List<GetAllReservationsResponse> getAllReservations(@RequestParam("id") int hotelId){
        return reservationService.getAllHotelReservations(hotelId);
    }

    @GetMapping("/getAllByGuestId")
    private List<GetAllReservationsResponse> getAllGuestReservations(@RequestParam("id") int guestId){
        return reservationService.getAllGuestReservations(guestId);
    }

    @PutMapping("/update-status")
    public Result updateReservationStatus(@RequestParam("id") long id,
                                     @RequestParam("isApproved") boolean isApproved) {
        return reservationService.updateReservationStatus(id, isApproved);
    }

    @PutMapping("/cancel")
    public Result cancelReservation(@RequestParam("id") long id) {
        return reservationService.cancelReservation(id);
    }

    @GetMapping("/getReportByHotelId")
    public List<GetReservationReportResponse> getReservationsReport(
            @RequestParam("hotelId") int hotelId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return reservationService.getReservationsByHotelAndDates(hotelId, startDate, endDate);
    }

}
