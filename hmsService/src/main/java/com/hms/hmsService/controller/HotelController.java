package com.hms.hmsService.controller;

import com.hms.hmsService.entities.Hotel;
import com.hms.hmsService.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @PostMapping
    public ResponseEntity<Hotel> createUser(@RequestBody Hotel hotel) {
        Hotel hotel1 = hotelService.createHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotel1);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getSingleUser(@PathVariable String hotelId) {
//        Hotel hotel = hotelService.getHotelByHotelId(hotelId);
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getHotelByHotelId(hotelId));
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getAllUser() {
        List<Hotel> allHotels = hotelService.getAllHotels();
        return ResponseEntity.ok(allHotels);
    }

    @DeleteMapping("/{hotelId}")
    public ResponseEntity deleteUser(@PathVariable String hotelId) {
        hotelService.deleteHotel(hotelId);
        return ResponseEntity.ok("Deleted");
    }

    @PutMapping("/{hotelId}")
    public ResponseEntity<Hotel> updateUser(@RequestBody Hotel hotel, @PathVariable String hotelId) {
        Hotel hotel1 = hotelService.updateHotel(hotel, hotelId);
        return ResponseEntity.ok(hotel1);
    }

}
