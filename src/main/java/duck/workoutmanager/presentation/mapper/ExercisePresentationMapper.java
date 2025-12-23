package duck.workoutmanager.presentation.mapper;

import duck.workoutmanager.application.command.exercise.CreateExerciseCommand;
import duck.workoutmanager.application.domain.model.Exercise;
import duck.workoutmanager.application.utils.ParseAttributes;
import duck.workoutmanager.presentation.request.exercise.CreateExerciseRequest;
import duck.workoutmanager.presentation.response.exercise.CreateExerciseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExercisePresentationMapper {

    private final ParseAttributes parseAttributes;

    public CreateExerciseCommand toCommand(CreateExerciseRequest request) {
        return CreateExerciseCommand.builder()
                .name(request.getName())
                .description(request.getDescription())
                .muscleGroup(parseAttributes.parseMuscleGroup(request.getMuscleGroup()))
                .build();
    }


    public CreateExerciseResponse toResponse(Exercise exercise) {
        return CreateExerciseResponse.builder()
                .name(exercise.getName())
                .description(exercise.getDescription())
                .muscleGroup(exercise.getMuscleGroup().name())
                .trainerEmail(exercise.getTrainerEmail())
                .build();
    }
}
