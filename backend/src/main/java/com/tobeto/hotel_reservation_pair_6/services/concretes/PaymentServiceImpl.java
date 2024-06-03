package com.tobeto.hotel_reservation_pair_6.services.concretes;

import com.iyzipay.Options;
import com.iyzipay.model.*;
import com.iyzipay.request.CreatePaymentRequest;
import com.tobeto.hotel_reservation_pair_6.core.utilities.exceptions.types.BusinessException;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.Guest;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.Hotel;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.Payment;
import com.tobeto.hotel_reservation_pair_6.repositories.PaymentRepository;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.GuestService;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.HotelService;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.PaymentService;
import com.tobeto.hotel_reservation_pair_6.services.dtos.reservationDtos.requests.CreateReservationRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService{
    //TODO: Manager'a iyzico apiKey ve iyzico secretKey gibi bilgileri ekletip, ödeme almak için zorunlu hale getirilebilir.
    //TODO: Buradaki amaç her managerlar'ın iyzico hesabı oluşturmalarını talep etmek ve ödemelerini iyzico üzerinden almak olabilir.
    //TODO: Yukarıdaki maddeler yapıldığında Site'nin komisyonu ve Iyzico'nun komisyonu kesilerek ödeme gönderilmesi yapılabilir.

    private final PaymentRepository paymentRepository;
    private final HotelService hotelService;
    private final GuestService guestService;

    @Value("${iyzico.api.key}")
    private String apiKey;

    @Value("${iyzico.secret.key}")
    private String secretKey;

    @Value("${iyzico.base.url}")
    private String baseUrl;

    @Override
    public Payment createPayment(CreateReservationRequest createReservationRequest, double amount){

        Guest guest = guestService.findById(createReservationRequest.getGuestId());
        Hotel hotel = hotelService.findByRooms_Id(createReservationRequest.getRoomId());

        Options options = new Options();
        options.setApiKey(apiKey);
        options.setSecretKey(secretKey);
        options.setBaseUrl(baseUrl);

        CreatePaymentRequest iyzicoPaymentRequest = new CreatePaymentRequest();
        iyzicoPaymentRequest.setLocale(Locale.TR.getValue());
        iyzicoPaymentRequest.setConversationId("123456789");
        iyzicoPaymentRequest.setPrice(new BigDecimal(amount));
        iyzicoPaymentRequest.setPaidPrice(new BigDecimal(amount));
        iyzicoPaymentRequest.setInstallment(1);
        iyzicoPaymentRequest.setBasketId(hotel.getId().toString());
        iyzicoPaymentRequest.setPaymentChannel(PaymentChannel.WEB.name());
        iyzicoPaymentRequest.setPaymentGroup(PaymentGroup.PRODUCT.name());


        if (createReservationRequest.getCurrency().equals("TL")){
            iyzicoPaymentRequest.setCurrency(Currency.TRY.name());
        }
        else if (createReservationRequest.getCurrency().equals("USD")){
            iyzicoPaymentRequest.setCurrency(Currency.USD.name());
        } else if (createReservationRequest.getCurrency().isEmpty()) {
            throw new BusinessException("The currency can not be null.");
        } else {
            throw new BusinessException("The currency is not supported.");
        }

        Address address = new Address();
        address.setAddress(guest.getFirstAddress() + guest.getSecondAddressLine());
        address.setCity(guest.getProvince());
        address.setZipCode(guest.getPostalCode());
        address.setCountry(guest.getCountry());
        address.setContactName(guest.getFirstName()+ " " + guest.getLastName());
        iyzicoPaymentRequest.setShippingAddress(address);
        iyzicoPaymentRequest.setBillingAddress(address);


        List<BasketItem> basketItems = new ArrayList<>();
        BasketItem basketItem = new BasketItem();
        basketItem.setId(hotel.getId().toString());
        basketItem.setName(hotel.getHotelName());
        basketItem.setPrice(new BigDecimal(amount));
        basketItem.setItemType(BasketItemType.PHYSICAL.name());
        basketItem.setCategory1("Room reservation");
        basketItems.add(basketItem);
        iyzicoPaymentRequest.setBasketItems(basketItems);

        Buyer buyer = new Buyer();
        buyer.setId(guest.getId().toString());//guest.getId();
        buyer.setName(guest.getFirstName());
        buyer.setIdentityNumber("1234567891");
        buyer.setSurname(guest.getLastName());
        buyer.setGsmNumber(guest.getPhoneNumber());
        buyer.setEmail(guest.getEmail());
        buyer.setRegistrationAddress(guest.getFirstAddress() + " " + guest.getSecondAddressLine() + " "+ guest.getCity());
        buyer.setCity(guest.getProvince());
        buyer.setCountry(guest.getCountry());
        buyer.setZipCode(guest.getPostalCode());
        buyer.setGsmNumber(guest.getPhoneNumber());
        iyzicoPaymentRequest.setBuyer(buyer);


        PaymentCard paymentCard = new PaymentCard();
        paymentCard.setCardHolderName(createReservationRequest.getCardHolderName());
        paymentCard.setCardNumber(createReservationRequest.getCardNumber());
        paymentCard.setExpireMonth(createReservationRequest.getExpirationMonth());
        paymentCard.setExpireYear(createReservationRequest.getExpirationYear());
        paymentCard.setCvc(createReservationRequest.getCvc());
        paymentCard.setRegisterCard(0);
        iyzicoPaymentRequest.setPaymentCard(paymentCard);

        com.iyzipay.model.Payment.create(iyzicoPaymentRequest, options);

        Payment payment = new Payment();
        payment.setPaymentDate(LocalDateTime.now());
        payment.setAmount(amount);
        payment.setCurrency(createReservationRequest.getCurrency());
        payment.setGuest(guest);

        paymentRepository.save(payment);

        return payment;
    }

    @Override
    public void save(Payment payment) {
        paymentRepository.save(payment);
    }

}
