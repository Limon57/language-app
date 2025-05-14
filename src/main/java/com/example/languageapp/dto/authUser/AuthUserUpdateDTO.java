package com.example.languageapp.dto.authUser;

import lombok.Data;

@Data
public class AuthUserUpdateDTO {
    private String newEmail;
    private String newPhoneNumber;
}
