package duck.workoutmanager.application.service.exercise;

import duck.workoutmanager.application.command.exercise.UpdateExerciseCommand;
import duck.workoutmanager.application.command.exercise.UpdateExerciseStatusCommand;
import duck.workoutmanager.application.domain.enums.ExerciseStatusEnum;
import duck.workoutmanager.application.domain.exception.AuthorizationException;
import duck.workoutmanager.application.domain.exception.ObjectNotFoundException;
import duck.workoutmanager.application.domain.model.Exercise;
import duck.workoutmanager.application.port.in.exercise.UpdateExerciseUseCase;
import duck.workoutmanager.application.port.out.exercise.GetExercisePortOut;
import duck.workoutmanager.application.port.out.exercise.UpdateExercisePortOut;
import duck.workoutmanager.application.utils.AuthorizationUtils;
import duck.workoutmanager.application.utils.CheckAttribute;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateExerciseManagerService implements UpdateExerciseUseCase {

    private final AuthorizationUtils authorizationUtils;
    private final CheckAttribute checkAttribute;

    private final GetExercisePortOut getExercisePortOut;
    private final UpdateExercisePortOut updateExercisePortOut;


    @Override
    public ExerciseStatusEnum updateExerciseStatus(UpdateExerciseStatusCommand command) {

        Exercise exercise = getExercisePortOut.getById(command.getExerciseId());

        if (exercise == null) {
            log.error("Exercise not found with id: ({})", command.getExerciseId());
            throw new ObjectNotFoundException("Exercise not found");
        }

        String trainerEmail = authorizationUtils.getCurrentUserEmail();

        if (!exercise.getTrainerEmail().equals(trainerEmail)) {
            log.error("Unauthorized access to exercise with id: ({})", command.getExerciseId());
            throw new AuthorizationException("Unauthorized access to exercise");
        }

        exercise.setExerciseStatus(command.getExerciseStatus());

        Exercise updatedExercise = updateExercisePortOut.updateExercise(exercise);

        return updatedExercise.getExerciseStatus();
    }


    @Override
    public Exercise updateExercise(UpdateExerciseCommand command) {

        checkCommand(command);

        Exercise exercise = getExercisePortOut.getById(command.getExerciseId());

        if (exercise == null) {
            log.error("Exercise not found with id: ({})", command.getExerciseId());
            throw new ObjectNotFoundException("Exercise not found");
        }

        String trainerEmail = authorizationUtils.getCurrentUserEmail();

        if (!exercise.getTrainerEmail().equals(trainerEmail)) {
            log.error("Unauthorized access to exercise with id: ({})", command.getExerciseId());
            throw new AuthorizationException("Unauthorized access to exercise");
        }

        exercise.updateDetails(command.getName(), command.getDescription(), command.getMuscleGroup());

        return updateExercisePortOut.updateExercise(exercise);
    }


    private void checkCommand(UpdateExerciseCommand command) {
        checkAttribute.checkStringIsNullOrEmpty(command.getName(), "Exercise name");
        checkAttribute.checkStringIsShorterThan(command.getName(), 100, "Exercise name");
        checkAttribute.checkStringIsShorterThan(command.getDescription(), 500, "Exercise description");
    }

}
