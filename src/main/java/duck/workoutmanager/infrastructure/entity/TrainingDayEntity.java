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
@Table(name = "training_day")
public class TrainingDayEntity {

    @Id
    @Column(columnDefinition = "UUID")
    private UUID id;
    private String weekDay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "week_id", nullable = false)
    private WeekEntity week;

    @OneToMany(mappedBy = "trainingDay")
    private Set<PlannedExerciseEntity> plannedExercises;

}
