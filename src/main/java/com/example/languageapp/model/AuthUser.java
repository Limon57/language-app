package com.example.languageapp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "AuthUser")
public class AuthUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(nullable = false, unique = true)
    private  String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId") // Foreign key to User table
    private User user;


    public AuthUser(String email, String password, String phoneNumber){
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

}
