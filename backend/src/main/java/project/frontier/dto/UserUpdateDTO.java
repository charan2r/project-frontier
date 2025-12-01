package project.frontier.dto;

import lombok.Data;
import project.frontier.entity.enums.UserRole;
import project.frontier.entity.enums.UserSpecialization;
import java.time.LocalDateTime;

@Data
public class UserUpdateDTO {
    
    private String firstName;
    private String lastName;
    private String name;
    private LocalDateTime dateOfBirth;
    private String profileImageUrl;
    private String nationality;
    private String gender;
    private String bloodType;
    private UserRole role;
    private UserSpecialization specialization;
    private Integer totalMissionsCompleted;
    private Integer totalExperimentsConducted;
}
