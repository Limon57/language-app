package com.example.languageapp.service;

import com.example.languageapp.dto.authUser.AuthUserRequestDTO;
import com.example.languageapp.dto.authUser.AuthUserResponseDTO;
import com.example.languageapp.dto.authUser.AuthUserUpdateDTO;
import com.example.languageapp.model.AuthUser;
import com.example.languageapp.repository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthUserService {

    private final AuthUserRepository authUserRepository;

    @Autowired
    public AuthUserService(AuthUserRepository authUserRepository){
        this.authUserRepository = authUserRepository;
    }


    // Register new user
    public AuthUserResponseDTO registerUser(AuthUserRequestDTO requestDTO) {

        // Validate email and phone
        if (authUserRepository.findByEmail(requestDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already in use.");
        }
        if (authUserRepository.findByPhoneNumber(requestDTO.getPhoneNumber()).isPresent()) {
            throw new RuntimeException("Phone number is already in use.");
        }

        // Map DTO to entity
        AuthUser user = new AuthUser();
        user.setEmail(requestDTO.getEmail());
        user.setPassword(requestDTO.getPassword());
        user.setPhoneNumber(requestDTO.getPhoneNumber());

        AuthUser savedUser = authUserRepository.save(user);

        // Map entity to response DTO
        AuthUserResponseDTO responseDTO = new AuthUserResponseDTO();
        responseDTO.setId(savedUser.getId());
        responseDTO.setEmail(savedUser.getEmail());
        responseDTO.setPhoneNumber(savedUser.getPhoneNumber());

        return responseDTO;
    }


    public void updateUser(Long id, AuthUserUpdateDTO updateDTO) {
        AuthUser user = authUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (updateDTO.getNewEmail() != null &&
                authUserRepository.findByEmail(updateDTO.getNewEmail()).isPresent()) {
            throw new RuntimeException("New email is already in use.");
        }

        if (updateDTO.getNewPhoneNumber() != null &&
                authUserRepository.findByPhoneNumber(updateDTO.getNewPhoneNumber()).isPresent()) {
            throw new RuntimeException("New phone number is already in use.");
        }

        if (updateDTO.getNewEmail() != null) {
            user.setEmail(updateDTO.getNewEmail());
        }

        if (updateDTO.getNewPhoneNumber() != null) {
            user.setPhoneNumber(updateDTO.getNewPhoneNumber());
        }

        authUserRepository.save(user);
    }




    //retrieve user by email
    public Optional<AuthUser> findByEmail(String email){
        return authUserRepository.findByEmail(email);
    }

    //retrieve user by number
    public Optional<AuthUser> findByPhoneNumber(String phoneNumber){
        return authUserRepository.findByPhoneNumber(phoneNumber);
    }

    //retrieve user by id
    public Optional<AuthUser> findById(Long id){
        return authUserRepository.findById(id);
    }

    //delete user by email
    public void deleteByEmail(String email){
        if(!authUserRepository.findByEmail(email).isPresent())
            throw new RuntimeException("No account exist with this email");
        else{
            authUserRepository.deleteByEmail(email);
        }
    }

    // Update email
    public void updateEmail(Long userId, String newEmail) {

        //get the user by the id
        AuthUser authUser = authUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        //check if the email is in use
        if (authUserRepository.findByEmail(newEmail).isPresent()) {
            throw new RuntimeException("New email is already in use.");
        }

        //save email and user
        authUser.setEmail(newEmail);
        authUserRepository.save(authUser);
    }

    public void updateNumber(Long id, String number){

        //get the user
        AuthUser authUser = authUserRepository.findById(id)
                .orElseThrow(() -> new  RuntimeException("User not Found"));

        // check number
        if (authUserRepository.findByPhoneNumber(number).isPresent()){
            throw new RuntimeException("New number is already in use");
        }

        //save number and user
        authUser.setPhoneNumber(number);
        authUserRepository.save(authUser);
    }
}
