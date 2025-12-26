package duck.workoutmanager.application.port.in.exercise;

import duck.workoutmanager.application.command.exercise.UpdateExerciseCommand;
import duck.workoutmanager.application.command.exercise.UpdateExerciseStatusCommand;
import duck.workoutmanager.application.domain.enums.ExerciseStatusEnum;
import duck.workoutmanager.application.domain.model.Exercise;

public interface UpdateExerciseUseCase {

    ExerciseStatusEnum updateExerciseStatus(UpdateExerciseStatusCommand command);

    Exercise updateExercise(UpdateExerciseCommand command);
}
