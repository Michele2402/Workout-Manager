package duck.workoutmanager.infrastructure.service;

import duck.workoutmanager.application.domain.model.Exercise;
import duck.workoutmanager.application.port.out.exercise.CreateExercisePortOut;
import duck.workoutmanager.application.port.out.exercise.GetExercisePortOut;
import duck.workoutmanager.infrastructure.entity.ExerciseEntity;
import duck.workoutmanager.infrastructure.mapper.ExerciseInfrastructureMapper;
import duck.workoutmanager.infrastructure.repository.ExerciseJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExerciseAdapterService implements GetExercisePortOut, CreateExercisePortOut {

    private final ExerciseJpaRepository exerciseJpaRepository;

    private final ExerciseInfrastructureMapper exerciseMapper;

    @Override
    @Transactional
    public Exercise getByName(String name) {

        log.info("Start - get exercise by name: ({})", name);

        Optional<ExerciseEntity> optionalExerciseEntity = exerciseJpaRepository.getExerciseByName(name);

        log.info("End - get exercise by name: ({})", optionalExerciseEntity);

        return optionalExerciseEntity.map(exerciseMapper::toModel).orElse(null);
    }

    @Override
    public Exercise create(Exercise exercise) {

        log.info("Start - create exercise: ({})", exercise.getName());

        ExerciseEntity exerciseEntity = exerciseMapper.toEntity(exercise);
        ExerciseEntity createdExercise = exerciseJpaRepository.save(exerciseEntity);

        log.info("End - create exercise: ({})", exercise.getName());

        return exerciseMapper.toModel(createdExercise);
    }
}
