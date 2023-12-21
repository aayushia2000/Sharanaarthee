package com.rating.ratingService.services;

import com.rating.ratingService.entities.Rating;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RatingService {
    //create
    Rating create(Rating rating);

    //get all ratings
    List<Rating> getRatings();

    //get all by UserId
    List<Rating> getRatingByUserId(String userId);

    //get all by hotel
    List<Rating> getRatingByHotelId(String hotelId);
}
