package project.frontier.entity;

import jakarta.persistence.*;
import lombok.*;
import project.frontier.entity.enums.ModuleType;
import project.frontier.entity.enums.ModuleStatus;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "modules")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Module {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private ModuleType type;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ModuleStatus status = ModuleStatus.ONLINE;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "colony_id", nullable = false)
    private Colony colony;

    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Resource> resources;

    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Experiment> experiments;


}
