package project.frontier.dto;

import lombok.Data;
import project.frontier.entity.enums.UserRole;
import project.frontier.entity.enums.UserSpecialization;

@Data
public class UserUpdateDTO {
    private UserRole newUserRole;
    private UserSpecialization newUserSpecialization;
}
