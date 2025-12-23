package duck.workoutmanager.presentation.controller;

import duck.workoutmanager.application.domain.model.Exercise;
import duck.workoutmanager.application.port.in.exercise.CreateExerciseUseCase;
import duck.workoutmanager.presentation.mapper.ExercisePresentationMapper;
import duck.workoutmanager.presentation.request.exercise.CreateExerciseRequest;
import duck.workoutmanager.presentation.response.exercise.CreateExerciseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/exercises")
@CrossOrigin("*")
public class ExerciseController {

    private final ExercisePresentationMapper exerciseMapper;

    private final CreateExerciseUseCase createExerciseUseCase;

    @PostMapping
    public ResponseEntity<CreateExerciseResponse> createExercise(
            @RequestBody CreateExerciseRequest request
    ) {
        log.info("Start - create exercise: ({})", request.getName());

        Exercise exercise = createExerciseUseCase.createExercise(
                exerciseMapper.toCommand(request)
        );

        log.info("End - create exercise: ({})", request.getName());

        return ResponseEntity.ok(exerciseMapper.toResponse(exercise));
    }
}
