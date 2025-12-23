package duck.workoutmanager.application.port.in.exercise;

import duck.workoutmanager.application.command.exercise.CreateExerciseCommand;
import duck.workoutmanager.application.domain.model.Exercise;

public interface CreateExerciseUseCase {

    Exercise createExercise(CreateExerciseCommand exercise);
}
