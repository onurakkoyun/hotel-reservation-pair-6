package com.tobeto.hotel_reservation_pair_6.services.concretes;

import com.iyzipay.Options;
import com.iyzipay.model.Address;
import com.iyzipay.model.BasketItem;
import com.iyzipay.model.BasketItemType;
import com.iyzipay.model.Buyer;
import com.iyzipay.model.Locale;
import com.iyzipay.model.PaymentCard;
import com.iyzipay.model.PaymentChannel;
import com.iyzipay.model.PaymentGroup;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

        Options options = createOptions();

        CreatePaymentRequest iyzicoPaymentRequest =
                createPaymentRequest(createReservationRequest, amount, guest, hotel);

        com.iyzipay.model.Payment iyzicoPaymentResponse = com.iyzipay.model.Payment.create(iyzicoPaymentRequest, options);

        if (!"success".equalsIgnoreCase(iyzicoPaymentResponse.getStatus())) {
            throw new BusinessException("Payment failed : " + iyzicoPaymentResponse.getErrorMessage());
        }

        String paymentTransactionId = iyzicoPaymentResponse.getPaymentItems().get(0).getPaymentTransactionId();

        Payment payment = new Payment();
        payment.setPaymentDate(LocalDate.now());
        payment.setAmount(amount);
        payment.setCurrency(createReservationRequest.getCurrency());
        payment.setGuest(guest);
        payment.setPaymentTransactionId(paymentTransactionId);
        payment.setHotel(hotel);

        paymentRepository.save(payment);

        return payment;
    }


	@Override
	public void save(Payment payment) {
        paymentRepository.save(payment);
	}

    @Override
    public Payment findById(long paymentId) {
        return paymentRepository.findById(paymentId)
                .orElseThrow(() -> new BusinessException("Payment not found!"));
    }

    @Override
    public Options createOptions() {
        Options options = new Options();
        options.setApiKey(apiKey);
        options.setSecretKey(secretKey);
        options.setBaseUrl(baseUrl);
        return options;
    }

    @Override
    public CreatePaymentRequest createPaymentRequest(CreateReservationRequest createReservationRequest, double amount, Guest guest, Hotel hotel) {
        CreatePaymentRequest iyzicoPaymentRequest = new CreatePaymentRequest();
        iyzicoPaymentRequest.setLocale(Locale.TR.getValue());
        iyzicoPaymentRequest.setConversationId(generateUniqueId());
        iyzicoPaymentRequest.setPrice(new BigDecimal(amount));
        iyzicoPaymentRequest.setPaidPrice(new BigDecimal(amount));
        iyzicoPaymentRequest.setInstallment(1);
        iyzicoPaymentRequest.setBasketId(hotel.getId().toString());
        iyzicoPaymentRequest.setPaymentChannel(PaymentChannel.WEB.name());
        iyzicoPaymentRequest.setPaymentGroup(PaymentGroup.PRODUCT.name());
        iyzicoPaymentRequest.setCurrency(createReservationRequest.getCurrency().name());

        Address address = createAddress(guest);
        iyzicoPaymentRequest.setShippingAddress(address);
        iyzicoPaymentRequest.setBillingAddress(address);

        List<BasketItem> basketItems = new ArrayList<>();
        BasketItem basketItem = createBasketItem(hotel, amount);
        basketItems.add(basketItem);
        iyzicoPaymentRequest.setBasketItems(basketItems);

        Buyer buyer = createBuyer(guest);
        iyzicoPaymentRequest.setBuyer(buyer);

        PaymentCard paymentCard = createPaymentCard(createReservationRequest);
        iyzicoPaymentRequest.setPaymentCard(paymentCard);

        return iyzicoPaymentRequest;

    }

    @Override
    public Address createAddress(Guest guest) {
        Address address = new Address();
        address.setAddress(guest.getFirstAddress() + guest.getSecondAddressLine());
        address.setCity(guest.getProvince());
        address.setZipCode(guest.getPostalCode());
        address.setCountry(guest.getCountry());
        address.setContactName(guest.getFirstName() + " " + guest.getLastName());
        return address;
    }

    @Override
    public BasketItem createBasketItem(Hotel hotel, double amount) {
        BasketItem basketItem = new BasketItem();
        basketItem.setId(hotel.getId().toString());
        basketItem.setName(hotel.getHotelName());
        basketItem.setPrice(new BigDecimal(amount));
        basketItem.setItemType(BasketItemType.PHYSICAL.name());
        basketItem.setCategory1("Room reservation");
        return basketItem;
    }

    @Override
    public Buyer createBuyer(Guest guest) {
        Buyer buyer = new Buyer();
        buyer.setId(guest.getId().toString());
        buyer.setName(guest.getFirstName());
        buyer.setIdentityNumber("1234567891");
        buyer.setSurname(guest.getLastName());
        buyer.setGsmNumber(guest.getPhoneNumber());
        buyer.setEmail(guest.getEmail());
        buyer.setRegistrationAddress(guest.getFirstAddress() + " " + guest.getSecondAddressLine() + " " + guest.getCity());
        buyer.setCity(guest.getProvince());
        buyer.setCountry(guest.getCountry());
        buyer.setZipCode(guest.getPostalCode());
        return buyer;
    }

    @Override
    public PaymentCard createPaymentCard(CreateReservationRequest createReservationRequest) {
        PaymentCard paymentCard = new PaymentCard();
        paymentCard.setCardHolderName(createReservationRequest.getCardHolderName());
        paymentCard.setCardNumber(createReservationRequest.getCardNumber());
        paymentCard.setExpireMonth(createReservationRequest.getExpirationMonth());
        paymentCard.setExpireYear(createReservationRequest.getExpirationYear());
        paymentCard.setCvc(createReservationRequest.getCvc());
        return paymentCard;
    }

    public static String generateUniqueId() {
        Random random = new Random();
        StringBuilder uniqueId = new StringBuilder();

        // 9 haneli bir sayı oluşturuyoruz
        while (uniqueId.length() < 9) {
            int digit = random.nextInt(10); // 0-9 arası rastgele bir sayı
            uniqueId.append(digit);
        }

        return uniqueId.toString();
    }

}
