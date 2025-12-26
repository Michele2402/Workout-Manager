package duck.workoutmanager.presentation.controller;

import duck.workoutmanager.application.domain.enums.ExerciseStatusEnum;
import duck.workoutmanager.application.domain.model.Exercise;
import duck.workoutmanager.application.port.in.exercise.CreateExerciseUseCase;
import duck.workoutmanager.application.port.in.exercise.GetExerciseUseCase;
import duck.workoutmanager.application.port.in.exercise.UpdateExerciseUseCase;
import duck.workoutmanager.presentation.mapper.ExercisePresentationMapper;
import duck.workoutmanager.presentation.request.exercise.CreateExerciseRequest;
import duck.workoutmanager.presentation.request.exercise.UpdateExerciseRequest;
import duck.workoutmanager.presentation.request.exercise.UpdateExerciseStatusRequest;
import duck.workoutmanager.presentation.response.exercise.ExerciseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/exercises")
@CrossOrigin("*")
public class ExerciseController {

    private final ExercisePresentationMapper exerciseMapper;

    private final CreateExerciseUseCase createExerciseUseCase;
    private final GetExerciseUseCase getExerciseUseCase;
    private final UpdateExerciseUseCase updateExerciseUseCase;

    @PostMapping
    public ResponseEntity<ExerciseResponse> createExercise(
            @RequestBody CreateExerciseRequest request
    ) {
        log.info("Start - create exercise: ({})", request.getName());

        Exercise exercise = createExerciseUseCase.createExercise(
                exerciseMapper.toCommand(request)
        );

        log.info("End - create exercise: ({})", request.getName());

        return ResponseEntity.ok(exerciseMapper.toResponse(exercise));
    }


    @GetMapping("/all-trainer")
    public ResponseEntity<List<ExerciseResponse>> getAllByTrainer() {
        log.info("Start - get all trainer exercises");

        List<Exercise> exercises = getExerciseUseCase.getAllByTrainer();

        List<ExerciseResponse> responses = exercises.stream()
                .map(exerciseMapper::toResponse)
                .toList();

        log.info("End - get all trainer exercises");

        return ResponseEntity.ok(responses);
    }


    @PutMapping("/update-status")
    public ResponseEntity<String> updateExerciseStatus(
            @RequestBody UpdateExerciseStatusRequest request
    ) {
        log.info("Start - update exercise status: ({})", request.getExerciseId());

        ExerciseStatusEnum status = updateExerciseUseCase.updateExerciseStatus(exerciseMapper.toCommand(request));

        log.info("End - update exercise status: ({})", request.getExerciseId());

        return ResponseEntity.ok(status.name());
    }


    @PutMapping("/update")
    public ResponseEntity<ExerciseResponse> updateExercise(
            @RequestBody UpdateExerciseRequest request
    ) {
        log.info("Start - update exercise: ({})", request.getExerciseId());

        Exercise exercise = updateExerciseUseCase.updateExercise(exerciseMapper.toCommand(request));

        log.info("End - update exercise: ({})", request.getExerciseId());

        return ResponseEntity.ok(exerciseMapper.toResponse(exercise));
    }

}
