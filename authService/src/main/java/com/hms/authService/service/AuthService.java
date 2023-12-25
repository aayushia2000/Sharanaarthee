package com.hms.authService.service;

import com.hms.authService.entity.UserCredential;

public interface AuthService {

    public String saveuser(UserCredential userCredential);
    public String generateToken(String username);

    public void validateToken(String token);

}
