package com.user.UserService.controllers;

import com.user.UserService.entities.User;
import com.user.UserService.payload.ApiResponse;
import com.user.UserService.services.UserService;
import com.user.UserService.services.impl.UserServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.aspectj.bridge.MessageUtil;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }
    //    int retryCount = 1;
    @GetMapping("/{userId}")
    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
//    @Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
//    @RateLimiter(name = "userRateLimiter",fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId) {
//        logger.info("retry count {}", retryCount);
//        retryCount++;
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }


    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex){
        logger.info("Fallback is executed because service is down", ex.getMessage());
        User user = new User();
        user.setName("Dummy");
        user.setUserId("1");
        user.setEmail("dummy01@gmail.com");
        user.setAbout("This is dummy user because service is down");
//        User user = User.builder().userId("1").name("Dummy").email("dummy01@gmail.com").about("This is dummy user because service is down").build();
        return  new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        List<User> allUser = userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return new ResponseEntity(new ApiResponse("User with userId" + userId + "deleted successfully"), HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable String userId) {

        User user1 = userService.updateUser(user, userId);
        return ResponseEntity.ok(user1);
    }


}
