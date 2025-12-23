package duck.workoutmanager.application.service.exercise;

import duck.workoutmanager.application.domain.model.Exercise;
import duck.workoutmanager.application.port.in.exercise.GetExerciseUseCase;
import duck.workoutmanager.application.port.out.exercise.GetExercisePortOut;
import duck.workoutmanager.application.utils.AuthorizationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetExerciseManagerService implements GetExerciseUseCase {

    private final AuthorizationUtils authorizationUtils;

    private final GetExercisePortOut getExercisePortOut;


    @Override
    public List<Exercise> getAllByTrainer() {

        String trainerEmail = authorizationUtils.getCurrentUserEmail();

        return getExercisePortOut.getAllByTrainer(trainerEmail);
    }
}
