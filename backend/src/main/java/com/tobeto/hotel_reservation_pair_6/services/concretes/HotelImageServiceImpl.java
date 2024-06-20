package com.tobeto.hotel_reservation_pair_6.services.concretes;

import com.tobeto.hotel_reservation_pair_6.core.results.*;
import com.tobeto.hotel_reservation_pair_6.core.services.concretes.CloudinaryService;
import com.tobeto.hotel_reservation_pair_6.core.utilities.exceptions.types.BusinessException;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.Hotel;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.HotelImage;
import com.tobeto.hotel_reservation_pair_6.repositories.HotelImageRepository;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.HotelImageService;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.HotelService;
import com.tobeto.hotel_reservation_pair_6.services.dtos.hotelImageDtos.responses.GetHotelImageResponse;
import com.tobeto.hotel_reservation_pair_6.services.mappers.HotelImageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class HotelImageServiceImpl implements HotelImageService {

    private final HotelImageRepository hotelImageRepository;

    private final HotelService hotelService;

    private final CloudinaryService cloudinaryService;

    @Override
    public Result uploadHotelImages(MultipartFile[] files, int hotelId) throws IOException {
        try {
            Hotel hotel = hotelService.findById(hotelId);

            List<HotelImage> hotelImages = new ArrayList<>();

            for (MultipartFile file : files) {
                String url = cloudinaryService.uploadFile(file);

                HotelImage hotelImage = new HotelImage();
                hotelImage.setUrl(url);
                hotelImage.setHotel(hotel);

                hotelImages.add(hotelImageRepository.save(hotelImage));

            }
            return new SuccessResult("Images uploaded.");
        } catch (IOException e) {
            return new ErrorResult("Hotel image upload failed : " + e.getMessage());
        }
    }

    @Override
    public List<GetHotelImageResponse> getImagesByHotelId(int hotelId) {
        List<HotelImage> hotelImages = hotelImageRepository.findByHotel_Id(hotelId);

        return hotelImages.stream().map(image -> {
            GetHotelImageResponse response = HotelImageMapper.INSTANCE.mapImageToGetHotelImageResponse(image);
            return response;
        }).collect(Collectors.toList());

    }
}
