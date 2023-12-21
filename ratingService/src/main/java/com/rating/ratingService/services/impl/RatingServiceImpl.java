package com.rating.ratingService.services.impl;

import com.rating.ratingService.entities.Rating;
import com.rating.ratingService.repositories.RatingRepository;
import com.rating.ratingService.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepository ratingRepository;
    //save a new rating
    @Override
    public Rating create(Rating rating) {
        String randomratingId = UUID.randomUUID().toString();
        rating.setRatingId(randomratingId);
        return ratingRepository.save(rating);
    }
// get all ratings
    @Override
    public List<Rating> getRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
          return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }
}
