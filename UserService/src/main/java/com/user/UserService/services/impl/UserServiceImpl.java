package com.user.UserService.services.impl;

import com.user.UserService.entities.Hotel;
import com.user.UserService.entities.Rating;
import com.user.UserService.entities.User;
import com.user.UserService.exceptions.ResourceNotFoundException;
import com.user.UserService.repositories.UserRepository;
import com.user.UserService.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public User saveUser(User user) {
//for generating unique userId
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        //Implement Rating Service call using Rest Template
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server!! : " + userId));

//      ArrayList<Rating> forObject =restTemplate.getForObject("http://localhost:8083/ratings/Users/05a0e871-f3e5-4158-a154-c826859d97b0", ArrayList.class);

        Rating[] ratingsOfUser =restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);

        logger.info("{}", ratingsOfUser);

        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

        List<Rating> ratingList = ratings.stream().map(rating -> {
            //we will call hotel service to get the hotel
             ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
            Hotel hotel = forEntity.getBody();
            logger.info("response status code: {}", forEntity.getStatusCode());
             //then we will set the hotel to rating
            rating.setHotel(hotel);
            //in the last we will return rating like "return rating;"
            return  rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);

        return user;

    }

    @Override
    public void deleteUser(String userId) {
       User user =  userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server!! : " + userId));
        userRepository.delete(user);
    }

    @Override
    public User updateUser(User user, String userId) {
        User user1 = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server!! : " + userId));
        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setAbout(user.getAbout());
        return userRepository.save(user1);
    }
}
