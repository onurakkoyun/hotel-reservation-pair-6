package com.tobeto.hotel_reservation_pair_6.services.concretes;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tobeto.hotel_reservation_pair_6.core.results.*;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.Hotel;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.Image;
import com.tobeto.hotel_reservation_pair_6.repositories.ImageRepository;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.ImageService;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.HotelService;
import com.tobeto.hotel_reservation_pair_6.services.dtos.imageDtos.responses.GetHotelImageResponse;
import com.tobeto.hotel_reservation_pair_6.services.mappers.ImageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    private final HotelService hotelService;

    private final Cloudinary cloudinary;

    @Override
    public Result uploadHotelImages(MultipartFile[] files, int hotelId) throws IOException {
        try {
            Hotel hotel = hotelService.findById(hotelId);

            List<Image> images = new ArrayList<>();

            for (MultipartFile file : files) {
                Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
                String url = (String) uploadResult.get("url");

                Image image = new Image();
                image.setUrl(url);
                image.setHotel(hotel);

                images.add(imageRepository.save(image));

            }
            return new SuccessResult("Images uploaded successfully");
        } catch (IOException e) {
            return new ErrorResult("Image upload failed: " + e.getMessage());
        }
    }

    @Override
    public List<GetHotelImageResponse> getImagesByHotelId(int hotelId) {
        List<Image> images = imageRepository.findByHotelId(hotelId);

        List<GetHotelImageResponse> responses = new ArrayList<>();


        return images.stream().map(image -> {
            GetHotelImageResponse response = ImageMapper.INSTANCE.mapImageToGetHotelImageResponse(image);
            return response;
        }).collect(Collectors.toList());

    }
}
