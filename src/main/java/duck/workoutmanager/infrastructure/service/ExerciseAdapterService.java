package duck.workoutmanager.infrastructure.service;

import duck.workoutmanager.application.domain.model.Exercise;
import duck.workoutmanager.application.port.out.exercise.CreateExercisePortOut;
import duck.workoutmanager.application.port.out.exercise.GetExercisePortOut;
import duck.workoutmanager.application.port.out.exercise.UpdateExercisePortOut;
import duck.workoutmanager.infrastructure.entity.ExerciseEntity;
import duck.workoutmanager.infrastructure.mapper.ExerciseInfrastructureMapper;
import duck.workoutmanager.infrastructure.repository.ExerciseJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExerciseAdapterService implements
        GetExercisePortOut,
        CreateExercisePortOut,
        UpdateExercisePortOut
{

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


    @Override
    public List<Exercise> getAllByTrainer(String email) {

        log.info("Start - get all exercises by trainer: ({})", email);

        List<ExerciseEntity> exerciseEntities = exerciseJpaRepository.getAllByTrainerEmail(email);

        log.info("End - get all exercises by trainer: ({})", email);

        return exerciseEntities.stream().map(exerciseMapper::toModel).toList();
    }


    @Override
    public Exercise getById(UUID id) {
        log.info("Start - get exercise by id: ({})", id);

        Optional<ExerciseEntity> optionalExerciseEntity = exerciseJpaRepository.findById(id);

        log.info("End - get exercise by id: ({})", id);

        return optionalExerciseEntity.map(exerciseMapper::toModel).orElse(null);
    }


    @Override
    public Exercise updateExercise(Exercise exercise) {
        log.info("Start - update exercise: ({})", exercise.getId());

        ExerciseEntity exerciseEntity = exerciseMapper.toEntity(exercise);
        ExerciseEntity updatedExercise = exerciseJpaRepository.save(exerciseEntity);

        log.info("End - update exercise: ({})", exercise.getId());

        return exerciseMapper.toModel(updatedExercise);
    }
}
