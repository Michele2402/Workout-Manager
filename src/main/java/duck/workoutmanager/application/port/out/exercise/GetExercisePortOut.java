package duck.workoutmanager.application.port.out.exercise;

import duck.workoutmanager.application.domain.model.Exercise;

import java.util.List;
import java.util.UUID;

public interface GetExercisePortOut {

    Exercise getByName(String name);

    List<Exercise> getAllByTrainer(String email);

    Exercise getById(UUID id);
}
