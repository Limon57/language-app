package com.example.languageapp.controller;

import com.example.languageapp.dto.authUser.AuthUserRequestDTO;
import com.example.languageapp.dto.authUser.AuthUserResponseDTO;
import com.example.languageapp.dto.authUser.AuthUserUpdateDTO;
import com.example.languageapp.model.AuthUser;
import com.example.languageapp.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthUserController {

    private final AuthUserService authUserService;

    @Autowired
    public AuthUserController(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    // Register new user
    @PostMapping("/register")
    public AuthUserResponseDTO register(@RequestBody AuthUserRequestDTO requestDTO) {
        return authUserService.registerUser(requestDTO);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody AuthUserUpdateDTO updateDTO) {
        authUserService.updateUser(id, updateDTO);
    }


    // Get user by email
    @GetMapping("/email")
    public Optional<AuthUser> getByEmail(@RequestParam String email) {
        return authUserService.findByEmail(email);
    }

    // Get user by phone number
    @GetMapping("/phone")
    public Optional<AuthUser> getByPhone(@RequestParam String phoneNumber) {
        return authUserService.findByPhoneNumber(phoneNumber);
    }

    // Get user by ID
    @GetMapping("/{id}")
    public Optional<AuthUser> getById(@PathVariable Long id) {
        return authUserService.findById(id);
    }

    // Delete user by email
    @DeleteMapping("/email")
    public void deleteByEmail(@RequestParam String email) {
        authUserService.deleteByEmail(email);
    }

    // Update user email
    @PutMapping("/{id}/email")
    public void updateEmail(@PathVariable Long id, @RequestParam String newEmail) {
        authUserService.updateEmail(id, newEmail);
    }

    // Update phone number
    @PutMapping("/{id}/phone")
    public void updatePhone(@PathVariable Long id, @RequestParam String newNumber) {
        authUserService.updateNumber(id, newNumber);
    }
}
