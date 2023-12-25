package com.user.UserService.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users_micro")
public class User {
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

    @Transient
    private List<Rating> ratings = new ArrayList<>();
}
