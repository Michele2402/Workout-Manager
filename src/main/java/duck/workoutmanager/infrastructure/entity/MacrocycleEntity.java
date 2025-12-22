package duck.workoutmanager.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "macrocycle")
public class MacrocycleEntity {

    @Id
    @Column(columnDefinition = "UUID")
    private UUID id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;

    @Column(length = 10000)
    private String coachNotes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_email", nullable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "macrocycle")
    private Set<MesocycleEntity> mesocycles;
}
