package duck.workoutmanager.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "week")
public class WeekEntity {

    @Id
    @Column(columnDefinition = "UUID")
    private UUID id;
    private int weekNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mesocycle_id", nullable = false)
    private MesocycleEntity mesocycle;

    @OneToMany(mappedBy = "week")
    private Set<TrainingDayEntity> trainingDays;
}
