package com.example.languageapp.dto.authUser;

import lombok.Data;

@Data
public class AuthUserRequestDTO {
    private String email;
    private String password;
    private String phoneNumber;
}
