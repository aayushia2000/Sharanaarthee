package com.hms.authService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users_micro")
public class UserCredential {
    @Id
    @Column(name = "ID")
    private String userId;
    @Column(name = "NAME", length = 50)
    private String name;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "ABOUT")
    private String about;
    @Column(name = "PASSWORD")
    private String password;
}
