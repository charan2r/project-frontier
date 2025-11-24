package project.frontier.entity;

import jakarta.persistence.*;
import lombok.*;
import project.frontier.entity.enums.UserRole;
import project.frontier.entity.enums.UserSpecialization;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable=false, unique=true)
    private String firebaseUid;

    @Column(nullable=false)
    private String email;

    @Column(nullable=false)
    private String name;

    private String firstName;
    private String lastName;
    private LocalDateTime dateOfBirth;
    private String profileImageUrl;

    private String nationality;
    private String gender;
    private String bloodType;
    private Integer totalMissionsCompleted;
    private Integer totalExperimentsConducted;

    @Builder.Default
    private LocalDateTime joinedDate = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private UserRole role = UserRole.COLONIST;

    @Enumerated(EnumType.STRING)
    private UserSpecialization specialization;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Colony> colonies;

    @OneToMany(mappedBy = "conductedBy")
    private List<Experiment> experiments;

    @PrePersist
    @PreUpdate
    private void setName() {
        if (this.name == null || this.name.trim().isEmpty()) {
            if (firstName != null && lastName != null) {
                this.name = firstName + " " + lastName;
            } else if (firstName != null) {
                this.name = firstName;
            } else if (lastName != null) {
                this.name = lastName;
            } else if (email != null) {
                this.name = email.split("@")[0];
            } else {
                this.name = "Unknown User";
            }
        }
    }
}
