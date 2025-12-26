package duck.workoutmanager.presentation.mapper;

import duck.workoutmanager.application.command.exercise.CreateExerciseCommand;
import duck.workoutmanager.application.command.exercise.UpdateExerciseCommand;
import duck.workoutmanager.application.command.exercise.UpdateExerciseStatusCommand;
import duck.workoutmanager.application.domain.model.Exercise;
import duck.workoutmanager.application.utils.ParseAttributes;
import duck.workoutmanager.presentation.request.exercise.CreateExerciseRequest;
import duck.workoutmanager.presentation.request.exercise.UpdateExerciseRequest;
import duck.workoutmanager.presentation.request.exercise.UpdateExerciseStatusRequest;
import duck.workoutmanager.presentation.response.exercise.ExerciseResponse;
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

    public UpdateExerciseStatusCommand toCommand(UpdateExerciseStatusRequest request) {
        return UpdateExerciseStatusCommand.builder()
                .exerciseId(parseAttributes.parseUUID(request.getExerciseId()))
                .exerciseStatus(parseAttributes.parseExerciseStatus(request.getExerciseStatus()))
                .build();
    }

    public UpdateExerciseCommand toCommand(UpdateExerciseRequest request) {
        return UpdateExerciseCommand.builder()
                .exerciseId(parseAttributes.parseUUID(request.getExerciseId()))
                .name(request.getName())
                .description(request.getDescription())
                .muscleGroup(parseAttributes.parseMuscleGroup(request.getMuscleGroup()))
                .build();
    }


    public ExerciseResponse toResponse(Exercise exercise) {
        return ExerciseResponse.builder()
                .id(exercise.getId().toString())
                .name(exercise.getName())
                .description(exercise.getDescription())
                .muscleGroup(exercise.getMuscleGroup().name())
                .exerciseStatus(exercise.getExerciseStatus().name())
                .trainerEmail(exercise.getTrainerEmail())
                .build();
    }
}
