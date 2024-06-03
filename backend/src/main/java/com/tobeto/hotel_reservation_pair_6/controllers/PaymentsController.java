package com.tobeto.hotel_reservation_pair_6.controllers;

import com.iyzipay.model.Payment;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.PaymentService;
import com.tobeto.hotel_reservation_pair_6.services.dtos.reservationDtos.requests.CreateReservationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments")
public class PaymentsController {
    private final PaymentService paymentService;

    /*@PostMapping("/create")
    public Payment createPayment(@RequestBody CreateReservationRequest request) {
        return paymentService.createPayment(request);
    }*/
}
