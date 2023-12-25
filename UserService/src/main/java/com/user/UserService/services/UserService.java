package com.user.UserService.services;

import com.user.UserService.entities.User;

import java.util.List;

public interface UserService {
    //get all user
    List<User> getAllUser();

    //get single user based on ID

    User getUser(String userId);

    //deletev
     void deleteUser(String userId);

    //update

    User updateUser(User user,String userId);



}
