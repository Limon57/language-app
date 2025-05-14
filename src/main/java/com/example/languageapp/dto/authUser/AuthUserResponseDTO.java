package com.example.languageapp.dto.authUser;

import lombok.Data;

@Data
public class AuthUserResponseDTO {
    private Long id;
    private String email;
    private String phoneNumber;
}
