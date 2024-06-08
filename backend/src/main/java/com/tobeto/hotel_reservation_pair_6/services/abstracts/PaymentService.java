package com.tobeto.hotel_reservation_pair_6.services.abstracts;

import com.iyzipay.Options;
import com.iyzipay.model.Address;
import com.iyzipay.model.BasketItem;
import com.iyzipay.model.Buyer;
import com.iyzipay.model.PaymentCard;
import com.iyzipay.request.CreatePaymentRequest;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.Guest;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.Hotel;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.Payment;
import com.tobeto.hotel_reservation_pair_6.services.dtos.reservationDtos.requests.CreateReservationRequest;

public interface PaymentService {
    Payment createPayment(CreateReservationRequest createReservationRequest, double amount);

    void save(Payment payment);

    Payment findById(long paymentId);

    Options createOptions();

    CreatePaymentRequest createPaymentRequest(CreateReservationRequest createReservationRequest, double amount, Guest guest, Hotel hotel);

    Address createAddress(Guest guest);

    BasketItem createBasketItem(Hotel hotel, double amount);

    Buyer createBuyer(Guest guest);

    PaymentCard createPaymentCard(CreateReservationRequest createReservationRequest);

}
