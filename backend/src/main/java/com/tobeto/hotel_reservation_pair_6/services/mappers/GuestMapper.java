package com.tobeto.hotel_reservation_pair_6.services.mappers;

import com.tobeto.hotel_reservation_pair_6.entities.concretes.Guest;
import com.tobeto.hotel_reservation_pair_6.services.dtos.guestDtos.requests.RegisterGuestRequest;
import com.tobeto.hotel_reservation_pair_6.services.dtos.guestDtos.requests.UpdateGuestRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GuestMapper {
    GuestMapper INSTANCE =  Mappers.getMapper(GuestMapper.class);

    Guest mapRegisterGuestRequestToGuest(RegisterGuestRequest request);

    Guest mapUpdateGuestRequestToGuest(UpdateGuestRequest request);
}