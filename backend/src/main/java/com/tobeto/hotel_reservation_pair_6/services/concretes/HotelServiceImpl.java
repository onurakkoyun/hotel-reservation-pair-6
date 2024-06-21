package com.tobeto.hotel_reservation_pair_6.services.concretes;

import com.tobeto.hotel_reservation_pair_6.core.results.Result;
import com.tobeto.hotel_reservation_pair_6.core.results.SuccessResult;
import com.tobeto.hotel_reservation_pair_6.core.services.concretes.CloudinaryService;
import com.tobeto.hotel_reservation_pair_6.core.utilities.exceptions.types.BusinessException;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.Hotel;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.HotelImage;
import com.tobeto.hotel_reservation_pair_6.repositories.HotelRepository;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.HotelImageService;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.HotelService;
import com.tobeto.hotel_reservation_pair_6.services.dtos.hotelDtos.requests.AddHotelRequest;
import com.tobeto.hotel_reservation_pair_6.services.dtos.hotelDtos.responses.GetAllHotelsResponse;
import com.tobeto.hotel_reservation_pair_6.services.mappers.HotelMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class HotelServiceImpl implements HotelService{

    private final HotelRepository hotelRepository;

    private final HotelImageService hotelImageService;

    private final CloudinaryService cloudinaryService;

    @Override
    public Result add(AddHotelRequest request) throws IOException{
        Hotel hotel = HotelMapper.INSTANCE.mapAddHotelRequestToHotel(request);

        List<HotelImage> hotelImages = new ArrayList<>();

        for (MultipartFile file : request.getPhotos()) {
            String url = cloudinaryService.uploadFile(file);

            HotelImage hotelImage = new HotelImage();
            hotelImage.setUrl(url);
            hotelImage.setHotel(hotel);

            hotelImages.add(hotelImage);
        }

        hotel.setHotelImages(hotelImages);

        hotelRepository.save(hotel);

        hotelImageService.saveAll(hotelImages);

        return new SuccessResult("Hotel added.");
    }

    @Override
    public Hotel findByRooms_Id(long id) {
        return hotelRepository.findByRooms_Id(id);
    }

    @Override
    public Hotel findById(int hotelId) {
        return hotelRepository.findById(hotelId)
                .orElseThrow(() -> new BusinessException("Hotel not found."));
    }

    @Override
    public List<GetAllHotelsResponse> searchHotels(String searchText) {

        List<Hotel> hotels = hotelRepository.searchHotels(searchText);

        return hotels.stream().map(hotel -> {
            GetAllHotelsResponse response = HotelMapper.INSTANCE.mapHotelToGetAllHotelsResponse(hotel);
            return response;
        }).collect(Collectors.toList());
    }

}
