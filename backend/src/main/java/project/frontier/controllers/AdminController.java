package project.frontier.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import lombok.RequiredArgsConstructor;
import project.frontier.dto.UserUpdateDTO;
import project.frontier.dto.UserResponseDTO;
import project.frontier.entity.User;
import project.frontier.services.UserService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

  
    // Get one user profile 
    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable UUID id) {
        return userService.findById(id)
                .map(user -> ResponseEntity.ok(UserResponseDTO.toDTO(user)))
                .orElse(ResponseEntity.notFound().build());
    }
    
   
    // Get all users 
    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> users = userService.getAllUsers()
                .stream()
                .map(UserResponseDTO::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }
    
    
    // Update user profile 
    @PutMapping("/users/{id}")
    public ResponseEntity<UserResponseDTO> updateUserProfile(
            @PathVariable UUID id, 
            @RequestBody UserUpdateDTO updateDTO) {
        try {
            User updatedUser = userService.updateUserProfile(id, updateDTO);
            return ResponseEntity.ok(UserResponseDTO.toDTO(updatedUser));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
   
}
