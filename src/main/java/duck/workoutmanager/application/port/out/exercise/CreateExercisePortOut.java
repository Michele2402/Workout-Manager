package duck.workoutmanager.application.port.out.exercise;

import duck.workoutmanager.application.domain.model.Exercise;

public interface CreateExercisePortOut {
    Exercise create(Exercise exercise);
}
