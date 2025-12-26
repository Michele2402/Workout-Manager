package duck.workoutmanager.application.service.exercise;

import duck.workoutmanager.application.command.exercise.CreateExerciseCommand;
import duck.workoutmanager.application.domain.enums.ExerciseStatusEnum;
import duck.workoutmanager.application.domain.exception.AlreadyExistsException;
import duck.workoutmanager.application.domain.model.Exercise;
import duck.workoutmanager.application.port.in.exercise.CreateExerciseUseCase;
import duck.workoutmanager.application.port.out.exercise.CreateExercisePortOut;
import duck.workoutmanager.application.port.out.exercise.GetExercisePortOut;
import duck.workoutmanager.application.utils.AuthorizationUtils;
import duck.workoutmanager.application.utils.CheckAttribute;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateExerciseManagerService implements CreateExerciseUseCase {

    private final CheckAttribute checkAttribute;
    private final AuthorizationUtils authorizationUtils;

    private final GetExercisePortOut getExercisePortOut;
    private final CreateExercisePortOut createExercisePortOut;

    @Override
    public Exercise createExercise(CreateExerciseCommand command) {

        checkCommand(command);

        String trainerEmail = authorizationUtils.getCurrentUserEmail();

        Exercise exercise = Exercise.builder()
                .id(UUID.randomUUID())
                .name(command.getName())
                .description(command.getDescription())
                .muscleGroup(command.getMuscleGroup())
                .trainerEmail(trainerEmail)
                .exerciseStatus(ExerciseStatusEnum.ACTIVE)
                .build();

        Exercise existingExercise = getExercisePortOut.getByName(exercise.getName());

        if (existingExercise != null) {
            log.error("Exercise with name ({}) already exists", exercise.getName());
            throw new AlreadyExistsException("Exercise with the same name already exists");
        }

        return createExercisePortOut.create(exercise);
    }


    private void checkCommand(CreateExerciseCommand command) {
        checkAttribute.checkStringIsNotNullOrEmpty(command.getName(), "Exercise name");
        checkAttribute.checkStringIsShorterThan(command.getName(), 100, "Exercise name");
        checkAttribute.checkStringIsShorterThan(command.getDescription(), 500, "Exercise description");
    }
}
