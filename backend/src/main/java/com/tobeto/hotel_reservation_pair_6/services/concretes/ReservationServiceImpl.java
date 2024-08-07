package com.tobeto.hotel_reservation_pair_6.services.concretes;

import com.tobeto.hotel_reservation_pair_6.core.results.ErrorResult;
import com.tobeto.hotel_reservation_pair_6.core.results.Result;
import com.tobeto.hotel_reservation_pair_6.core.results.SuccessResult;
import com.tobeto.hotel_reservation_pair_6.core.utilities.configurations.email.EmailConfig;
import com.tobeto.hotel_reservation_pair_6.core.utilities.exceptions.types.BusinessException;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.*;
import com.tobeto.hotel_reservation_pair_6.entities.enums.ReservationStatus;
import com.tobeto.hotel_reservation_pair_6.repositories.ReservationRepository;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.*;
import com.tobeto.hotel_reservation_pair_6.services.dtos.reservationDtos.requests.CreateReservationRequest;
import com.tobeto.hotel_reservation_pair_6.services.dtos.reservationDtos.responses.GetAllReservationsResponse;
import com.tobeto.hotel_reservation_pair_6.services.dtos.reservationDtos.responses.GetReservationReportResponse;
import com.tobeto.hotel_reservation_pair_6.services.mappers.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService{
    private final ReservationRepository reservationRepository;
    private final ReservationStatusService reservationStatusService;
    private final PaymentService paymentService;
    private final GuestService guestService;
    private final RefundPaymentService refundPaymentService;
    private final EmailConfig emailConfig;
    private final RoomService roomService;

    private final CurrencyExchangeService currencyExchangeService;

    @Transactional
    @Override
    public Result createReservation(CreateReservationRequest request){
        Guest guest = guestService.findById(request.getGuestId());
        Room room = roomService.findById(request.getRoomId());

        Reservation reservation = ReservationMapper.INSTANCE.mapCreateReservationRequestToReservation(request);
        List<ReservationStatus> reservationStatus = reservationStatusService.getAllReservationStatus();

        reservation.setStatus(reservationStatus.get(0)); //PENDING_APPROVAL_BY_HOTEL
        reservation.setCreatedDate(LocalDate.now());

        long daysBetween = reservation.getCheckOutDate().toEpochDay() - reservation.getCheckInDate().toEpochDay();
        double calculateAmount = room.getDailyPrice() * daysBetween;

        //Eğer oda fiyatının para birimi ile request'den gelen para birimi
        //eşit değil ise currencyExchangeService'den parite alınarak hesaplama yapıldı.
        if(!room.getCurrency().name().equals(reservation.getCurrency().name())){
            double exchangeRate;
            try {
                exchangeRate = currencyExchangeService
                        .getExchangeRate(room.getCurrency().name().toString(), reservation.getCurrency().name().toString());
            } catch (RuntimeException e) {
                return new ErrorResult( e.getMessage());
            }

            calculateAmount = calculateAmount * exchangeRate;
        }

        reservation.setAmount(calculateAmount);

        Payment payment = paymentService.createPayment(request, calculateAmount);//Burada save ediliyor.

        reservation.setAmount(calculateAmount);
        reservation.setGuest(guest);
        reservation.setRoom(room);

        payment.setReservation(reservation);

        paymentService.save(payment);

        roomService.save(room); // Save room with updated bookedRoomQuantity

        String managerEmailBody = "<strong>You have a new reservation!</strong><br/>" +
                "Please confirm your reservation in the system.<br/><br/>" +
                "<strong><u>Hotel Information</strong></u>" +
                "<br/>Hotel name :&nbsp;" + room.getHotel().getHotelName() +
                "<br/>Email :&nbsp;" + room.getHotel().getManager().getEmail() +
                "<br/>Phone number :&nbsp;" + room.getHotel().getManager().getPhoneNumber() +
                "<br/><br/><strong><u>Reservation Information</u></strong>"+
                "<br/>Reservation creation date :&nbsp;" + reservation.getCreatedDate()+
                "<br/>Room type :&nbsp;" + room.getRoomType().getRoomTypeName() +
                "<br/>Check in date :&nbsp;" + reservation.getCheckInDate() +
                "<br/>Check out date :&nbsp;" + reservation.getCheckOutDate() +
                "<br/>Reservation Fee :&nbsp;" + request.getCurrency().name() + " " + calculateAmount;


        String guestEmailBody ="<strong>Your reservation request has been received!</strong><br/>" +
                "Your reservation request has been forwarded to <u>" + room.getHotel().getHotelName() +".</u><br/><br/>" +
                "<strong><u>Guest Information</u></strong><br/>" +
                "Fullname :&nbsp;" + guest.getFirstName() + " " + guest.getLastName() +
                "<br/>Email :&nbsp;" + guest.getEmail() +
                "<br/><br/><strong><u>Reservation Information</u></strong>"+
                "<br/>Reservation creation date :&nbsp;" + reservation.getCreatedDate()+
                "<br/>Room type :&nbsp;" + room.getRoomType().getRoomTypeName() +
                "<br/>Check in date :&nbsp;" + reservation.getCheckInDate() +
                "<br/>Check out date :&nbsp;" + reservation.getCheckOutDate() +
                "<br/>Reservation Fee :&nbsp;" + request.getCurrency().name() + " " + calculateAmount;


        emailConfig.sendEmail(room.getHotel().getManager().getEmail(),
                "New Reservation Notification", managerEmailBody);

        emailConfig.sendEmail(guest.getEmail(), "New Reservation Notification", guestEmailBody);

        return new SuccessResult("The reservation has been created successfully.");
    }

    //Madde - 4.1
    @Override
    public List<GetAllReservationsResponse> getAllHotelReservations(int hotelId) {
        List<Reservation> reservations = reservationRepository.findByRoom_Hotel_Id(hotelId);

        return reservations.stream().map(reservation -> {
            GetAllReservationsResponse response = ReservationMapper.INSTANCE.mapReservationToGetAllReservationsResponse(reservation);
            return response;
        }).collect(Collectors.toList());
    }

    // Madde - 4.2
    @Override
    public List<GetAllReservationsResponse> getAllGuestReservations(int guestId) {
        List<Reservation> reservations = reservationRepository.findByGuest_Id(guestId);

        return reservations.stream().map(reservation -> {
            GetAllReservationsResponse response = ReservationMapper.INSTANCE.mapReservationToGetAllReservationsResponse(reservation);
            return response;
        }).collect(Collectors.toList());
    }


    //Madde - 4.1
    //Burada rezervasyon onaylanmadığı takdirde, Guest'e ücret iadesi yapıldı.
    @Override
    public Result updateReservationStatus(long id, boolean isApproved) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new BusinessException("The reservation does not found."));

        if (isApproved){
            //reservation.setStatus(ReservationStatus.APPROVED_BY_HOTEL);
            emailConfig.sendEmail(reservation.getGuest()
                    .getEmail(), "Your Reservation Approved!",
                    "Your reservation has been confirmed by the hotel.");
            emailConfig.sendEmail(reservation.getRoom()
                    .getHotel().getManager().getEmail(), "Room Reservation Approved!",
                    "Your reservation has been confirmed successfully.");
        }
        else {
            List<ReservationStatus> reservationStatus = reservationStatusService.getAllReservationStatus();

            reservation.setStatus(reservationStatus.get(2)); //CANCELED_BY_HOTEL

            String guestEmailBody ="<strong>Your reservation has been cancelled by Hotel!</strong><br/>" +
                    "<strong><u>Hotel Information</u></strong>" +
                    "<br/>Hotel name : " + reservation.getRoom().getHotel().getHotelName() +
                    "<br/>Email : " + reservation.getRoom().getHotel().getManager().getEmail() +
                    "<br/>Refunded Fee : " + reservation.getCurrency() + " " + reservation.getAmount();

            String hotelEmailBody ="<strong>Room reservation has been cancelled!</strong><br/>" +
                    "<strong><u>Guest Information</u></strong><br/>" +
                    "Fullname : " + reservation.getGuest().getFirstName() + " " + reservation.getGuest()
                    .getLastName() +
                    "<br/>Email : " + reservation.getGuest().getEmail() +
                    "<br/>Refunded Fee : " + reservation.getCurrency() + " " + reservation.getAmount();

            emailConfig.sendEmail(reservation.getGuest()
                    .getEmail(), "Your Reservation Cancelled", guestEmailBody);

            emailConfig.sendEmail(reservation.getRoom()
                    .getHotel().getManager().getEmail(), "Room Reservation Cancelled", hotelEmailBody);

            refundPaymentService.createRefund(reservation.getPayment().getId(), reservation.getAmount());
        }

        reservationRepository.save(reservation);

        return new SuccessResult();
    }

    //Cancel Reservation By Guest
    @Override
    public Result cancelReservation(long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new BusinessException("The reservation does not found."));

        List<ReservationStatus> reservationStatus = reservationStatusService.getAllReservationStatus();

        reservation.setStatus(reservationStatus.get(3)); //CANCELED_BY_GUEST

        refundPaymentService.createRefund(reservation.getPayment().getId(), reservation.getAmount());

        String guestEmailBody ="<strong>Your reservation has been cancelled!</strong><br/>" +
                "<strong><u>Hotel Information</u></strong>" +
                "<br/>Hotel name : " + reservation.getRoom().getHotel().getHotelName() +
                "<br/>Email : " + reservation.getRoom().getHotel().getManager().getEmail() +
                "<br/>Refunded Fee : " + reservation.getCurrency() + " " + reservation.getAmount();

        String hotelEmailBody ="<strong>Room reservation has been cancelled by guest!</strong><br/>" +
                "<strong><u>Guest Information</u></strong><br/>" +
                "Fullname : " + reservation.getGuest().getFirstName() + " " + reservation.getGuest()
                .getLastName() +
                "<br/>Email : " + reservation.getGuest().getEmail() +
                "<br/>Refunded Fee : " + reservation.getCurrency() + " " + reservation.getAmount();

        emailConfig.sendEmail(reservation.getGuest()
                .getEmail(), "Your Reservation Cancelled", guestEmailBody);

        emailConfig.sendEmail(reservation.getRoom()
                .getHotel().getManager().getEmail(), "Room Reservation Cancelled", hotelEmailBody);

        reservationRepository.save(reservation);

        return new SuccessResult("The reservation canceled successfully.");
    }

    @Override
    public List<GetReservationReportResponse> getReservationsByHotelAndDates(int hotelId, LocalDate startDate, LocalDate endDate) {

        List<Reservation> reservations = reservationRepository.findReservationsByHotelAndDates(hotelId, startDate, endDate);

        return reservations.stream().map(reservation -> {
            GetReservationReportResponse response = ReservationMapper.INSTANCE.mapReservationToGetReservationReportResponse(reservation);
            return response;
        }).collect(Collectors.toList());
    }

    @Override
    public List<Reservation> findReservationsByHotelIdAndGuestId(int hotelId, long guetsId) {
        return reservationRepository.findByRoom_Hotel_IdAndGuest_Id(hotelId, guetsId);
    }

}
