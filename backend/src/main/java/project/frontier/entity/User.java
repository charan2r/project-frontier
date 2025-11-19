package project.frontier.entity;

import jakarta.persistence.*;
import lombok.*;
import project.frontier.entity.enums.UserRole;
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

    private LocalDateTime joinedDate = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.COLONIST;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Colony> colonies;

    @OneToMany(mappedBy = "conductedBy")
    private List<Experiment> experiments;

    
}
