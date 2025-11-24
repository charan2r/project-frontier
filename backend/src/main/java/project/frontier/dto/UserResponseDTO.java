package project.frontier.dto;

import lombok.Data;
import lombok.Builder;
import project.frontier.entity.User;
import project.frontier.entity.enums.UserRole;
import project.frontier.entity.enums.UserSpecialization;
import java.time.LocalDateTime;

@Data
@Builder
public class UserResponseDTO {
    private String id;
    private String firebaseUid;
    private String email;
    private String name;
    private String firstName;
    private String lastName;
    private UserRole role;
    private UserSpecialization specialization;
    private String nationality;
    private String gender;
    private String bloodType;
    private String profileImageUrl;
    private Integer totalMissionsCompleted;
    private Integer totalExperimentsConducted;
    private LocalDateTime joinedDate;

    public static UserResponseDTO toDTO(User user) {
            String fullName = null;

            if (user.getFirstName() != null) {
                fullName = user.getFirstName();
                if (user.getLastName() != null) {
                    fullName += " " + user.getLastName();
                }
            }

            return UserResponseDTO.builder()
                    .id(user.getId().toString())
                    .firebaseUid(user.getFirebaseUid())
                    .email(user.getEmail())
                    .name(fullName)
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .role(user.getRole())
                    .specialization(user.getSpecialization())
                    .nationality(user.getNationality())
                    .gender(user.getGender())
                    .bloodType(user.getBloodType())
                    .profileImageUrl(user.getProfileImageUrl())
                    .totalMissionsCompleted(user.getTotalMissionsCompleted())
                    .totalExperimentsConducted(user.getTotalExperimentsConducted())
                    .joinedDate(user.getJoinedDate())
                    .build();
            }


}
