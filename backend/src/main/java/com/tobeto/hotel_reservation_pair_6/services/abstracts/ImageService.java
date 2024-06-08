package com.tobeto.hotel_reservation_pair_6.services.abstracts;

import com.tobeto.hotel_reservation_pair_6.core.results.DataResult;
import com.tobeto.hotel_reservation_pair_6.core.results.Result;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.Hotel;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.Image;
import com.tobeto.hotel_reservation_pair_6.services.dtos.imageDtos.responses.GetHotelImageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService {
    Result uploadHotelImages(MultipartFile[] files, int hotelId) throws IOException;

    List<GetHotelImageResponse> getImagesByHotelId(int hotelId);
}
