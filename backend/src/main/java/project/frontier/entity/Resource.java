package project.frontier.entity;

import jakarta.persistence.*;
import lombok.*;
import project.frontier.entity.enums.ResourceType;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "resources")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resource {
    @Id
    @GeneratedValue
    private UUID id;

    @Enumerated(EnumType.STRING)
    private ResourceType type;

    @Column(nullable = false)
    @Builder.Default
    private Double quantity = 0.0;

    @Column(nullable = false)
    private String unit;

    @ManyToOne
    @JoinColumn(name = "module_id", nullable = false)
    private Module module;

    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();
    
}
