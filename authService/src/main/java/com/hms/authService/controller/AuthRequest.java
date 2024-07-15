package com.hms.authService.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//auth request isliye banayi hai kyunki UserCredentials mein aur bhi parameters hain jinki humko zaroorat nhi hai

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    private String username;
    private String password;
}
