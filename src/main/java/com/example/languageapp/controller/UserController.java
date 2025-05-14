package com.example.languageapp.controller;

import com.example.languageapp.dto.user.UserRequestDTO;
import com.example.languageapp.dto.user.UserResponseDTO;
import com.example.languageapp.model.PortugueseModel;
import com.example.languageapp.model.User;
import com.example.languageapp.repository.PortugueseModelRepository;
import com.example.languageapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final PortugueseModelRepository portugueseModelRepository;

    @Autowired
    public UserController(UserService userService, PortugueseModelRepository portugueseModelRepository) {
        this.userService = userService;
        this.portugueseModelRepository = portugueseModelRepository;
    }

    // ✅ GET user by ID
    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable Long id) {
        User user = userService.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return toResponseDTO(user);
    }

    // ✅ CREATE new user
    @PostMapping
    public UserResponseDTO createUser(@RequestBody UserRequestDTO userRequestDTO) {
        PortugueseModel model = portugueseModelRepository.findById(userRequestDTO.getPortugueseModelId())
                .orElseThrow(() -> new RuntimeException("Portuguese model not found"));

        User user = new User();
        user.setPortugueseModel(model);

        User savedUser = userService.save(user);
        return toResponseDTO(savedUser);
    }

    // ✅ DELETE user
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    // 🔄 Mapping method
    private UserResponseDTO toResponseDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setPortugueseModelId(user.getPortugueseModel().getId());
        return dto;
    }
}
