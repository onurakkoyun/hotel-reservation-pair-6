package com.tobeto.hotel_reservation_pair_6.services.concretes;

import com.tobeto.hotel_reservation_pair_6.core.results.Result;
import com.tobeto.hotel_reservation_pair_6.core.results.SuccessResult;
import com.tobeto.hotel_reservation_pair_6.core.utilities.configurations.email.EmailConfiguration;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.Guest;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.Payment;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.Reservation;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.Room;
import com.tobeto.hotel_reservation_pair_6.entities.enums.ReservationStatus;
import com.tobeto.hotel_reservation_pair_6.repositories.ReservationRepository;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.GuestService;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.PaymentService;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.ReservationService;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.RoomService;
import com.tobeto.hotel_reservation_pair_6.services.dtos.reservationDtos.requests.CreateReservationRequest;
import com.tobeto.hotel_reservation_pair_6.services.mappers.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService{
    //TODO: Ödeme yapıldıktan sonra bookedRoomQuantity 1 arttırılıcak
    //TODO: ve Available roomlar listelenirken bookedRoomQunatity ler de sorguya eklenip kontrol edilmesi gerekecek.
    private final ReservationRepository reservationRepository;

    private final PaymentService paymentService;
    private final GuestService guestService;
    private final EmailConfiguration emailConfiguration;

    private final RoomService roomService;

    @Transactional
    @Override
    public Result createReservation(CreateReservationRequest request){
        Guest guest = guestService.findById(request.getGuestId());
        Room room = roomService.findById(request.getRoomId());

        Reservation reservation = ReservationMapper.INSTANCE.mapCreateReservationRequestToReservation(request);
        reservation.setStatus(ReservationStatus.PENDING_APPROVAL_BY_HOTEL);
        reservation.setCreatedDate(LocalDate.now());

        long daysBetween = reservation.getCheckOutDate().toEpochDay() - reservation.getCheckInDate().toEpochDay();
        double calculateAmount = room.getDailyPrice() * daysBetween;
        reservation.setAmount(calculateAmount);

        Payment payment = paymentService.createPayment(request, calculateAmount);

        reservation.setAmount(calculateAmount);
        reservation.setGuest(guest);
        reservation.setRoom(room);

        payment.setReservation(reservation);

        paymentService.save(payment);

        roomService.save(room); // Save room with updated bookedRoomQuantity

        //reservationRepository.save(reservation);

        String managerEmailBody = "<strong>You have a new reservation!</strong><br/>" +
                "Please confirm your reservation in the system.<br/><br/>" +
                "<strong>Guest Information</strong><br/>" +
                "Fullname : " + guest.getFirstName() + " " + guest.getLastName() +
                "<br/>Email : " + guest.getEmail() +
                "<br/><br/><strong>Reservation Information</strong>"+
                "<br/>Reservation creation date : " + reservation.getCreatedDate()+
                "<br/>Room type : " + room.getRoomType().getRoomTypeName() +
                "<br/>Check in date : " + reservation.getCheckInDate() +
                "<br/>Check out date : " + reservation.getCheckOutDate();
        if (request.getCurrency().equals("TL")){
            managerEmailBody += "<br/>Reservation Fee : " + calculateAmount + " " + request.getCurrency();
        } else if (request.getCurrency().equals("USD")) {
            managerEmailBody += "<br/>Reservation Fee : " + request.getCurrency() + calculateAmount;
        }
        else {
            managerEmailBody += "<br/>Reservation Fee : " + calculateAmount;
        }


        emailConfiguration.sendEmail(room.getHotel().getManager().getEmail(),
                "New Reservation Notification", managerEmailBody);

        return new SuccessResult("The reservation has been created successfully.");
    }

    /*@Override
    public List<Reservation> findReservationsForRoomInDateRange(Long roomId, LocalDate checkInDate, LocalDate checkOutDate) {
        return reservationRepository.findReservationsForRoomInDateRange(roomId, checkInDate, checkOutDate);
    }*/
}
