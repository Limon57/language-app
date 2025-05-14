package com.example.languageapp.service;

import com.example.languageapp.model.User;
import com.example.languageapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    //find user by id
    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

    //save user
    public User save(User user){
        return userRepository.save(user);
    }

    //delete user by id
    public void  deleteUser(Long id){
        userRepository.deleteById(id);
    }

}
