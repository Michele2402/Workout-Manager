package duck.workoutmanager.application.port.out.exercise;

import duck.workoutmanager.application.domain.model.Exercise;

public interface UpdateExercisePortOut {
    Exercise updateExercise(Exercise exercise);
}
