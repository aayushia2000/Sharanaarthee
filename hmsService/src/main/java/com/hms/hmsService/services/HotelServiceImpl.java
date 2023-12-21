package com.hms.hmsService.services;

import com.hms.hmsService.entities.Hotel;
import com.hms.hmsService.exceptions.ResourceNotFoundException;
import com.hms.hmsService.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService{

    @Autowired
    private HotelRepository hotelRepository;
    @Override
    public Hotel createHotel(Hotel hotel) {
        String randomHotelId = UUID.randomUUID().toString();
        hotel.setId(randomHotelId);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotelByHotelId(String id) {
        return hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("hotel with Id: "+id+" not found"));
    }

    @Override
    public void deleteHotel(String id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("hotel with Id: "+id+" not found"));
        hotelRepository.delete(hotel);

    }

    @Override
    public Hotel updateHotel(Hotel hotel, String id) {
        Hotel hotel1 = hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("hotel with Id: "+id+" not found"));
        hotel1.setName(hotel.getName());
        hotel1.setLocation(hotel.getLocation());
        hotel1.setAbout(hotel.getAbout());
        return hotelRepository.save(hotel1);
    }
}
