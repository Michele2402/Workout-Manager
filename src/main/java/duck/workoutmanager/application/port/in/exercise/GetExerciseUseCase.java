package duck.workoutmanager.application.port.in.exercise;

import duck.workoutmanager.application.domain.model.Exercise;

import java.util.List;

public interface GetExerciseUseCase {

    List<Exercise> getAllByTrainer();
}
