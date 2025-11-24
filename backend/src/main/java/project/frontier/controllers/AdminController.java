package project.frontier.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import project.frontier.dto.UserUpdateDTO;
import project.frontier.dto.UserResponseDTO;
import project.frontier.entity.User;
import project.frontier.services.UserService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @PutMapping("users/{uid}/role")
    public UserResponseDTO updateRole(@PathVariable String uid, @RequestBody UserUpdateDTO roleUpdateDTO){
        User updatedUser = userService.updateRole(uid, roleUpdateDTO.getNewUserRole());
        return UserResponseDTO.toDTO(updatedUser);
    }

    @PutMapping("users/{uid}/specialization")
    public UserResponseDTO updateSpecialization(@PathVariable String uid, @RequestBody UserUpdateDTO specializationUpdateDTO) {
        User specializedUser = userService.updateSpecialization(uid, specializationUpdateDTO.getNewUserSpecialization());
        return UserResponseDTO.toDTO(specializedUser);
    }
   
}
