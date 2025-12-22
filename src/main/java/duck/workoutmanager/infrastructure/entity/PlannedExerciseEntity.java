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
@Table(name = "planned_exercise")
public class PlannedExerciseEntity {

    @Id
    @Column(columnDefinition = "UUID")
    private UUID id;
    private int sets;
    private String timeUnderTension;
    private int restSeconds;

    @Column(length = 10000)
    private String coachNotes;

    @Column(length = 10000)
    private String userNotes;

    private LocalDate completionDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "training_day_id", nullable = false)
    private TrainingDayEntity trainingDay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id", nullable = false)
    private ExerciseEntity exercise;

    @OneToMany(mappedBy = "plannedExercise")
    private Set<ExerciseExecutionEntity> exerciseExecutions;
}
