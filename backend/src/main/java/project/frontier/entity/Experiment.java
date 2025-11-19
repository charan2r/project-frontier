package project.frontier.entity;

import jakarta.persistence.*;
import lombok.*;
import project.frontier.entity.enums.MissionStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "experiments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Experiment{
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String resultUrl; 

    @ManyToOne
    @JoinColumn(name = "conducted_by")
    private User conductedBy;

    @ManyToOne
    @JoinColumn(name = "module_id", nullable = false)
    private Module module;

    private LocalDateTime createdAt = LocalDateTime.now();
}