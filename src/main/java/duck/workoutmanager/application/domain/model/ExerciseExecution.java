package duck.workoutmanager.application.domain.model;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Slf4j
public class ExerciseExecution {

    private UUID id;
    private int targetReps;
    private int reps;
    private double weight;

    private PlannedExercise plannedExercise;

    @Builder
    public ExerciseExecution(UUID id, int targetReps, int reps, double weight, PlannedExercise plannedExercise) {
        this.id = id;
        this.targetReps = targetReps;
        this.reps = reps;
        this.weight = weight;
        this.plannedExercise = plannedExercise;
    }
}
