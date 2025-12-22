package duck.workoutmanager.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "exercise_execution")
public class ExerciseExecutionEntity {

    @Id
    @Column(columnDefinition = "UUID")
    private UUID id;
    private int targetReps;
    private int reps;
    private double weight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "planned_exercise_id", nullable = false)
    private PlannedExerciseEntity plannedExercise;
}
