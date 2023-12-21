package com.hms.hmsService.services;

import com.hms.hmsService.entities.Hotel;

import java.util.List;

public interface HotelService {
    Hotel createHotel(Hotel hotel);
    List<Hotel> getAllHotels();
    Hotel getHotelByHotelId(String id);

    void deleteHotel(String id);
    Hotel updateHotel(Hotel hotel,String id);
}
