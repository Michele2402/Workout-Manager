package duck.workoutmanager.infrastructure.mapper;

import duck.workoutmanager.application.domain.model.Exercise;
import duck.workoutmanager.infrastructure.entity.ExerciseEntity;
import org.springframework.stereotype.Component;

@Component
public class ExerciseInfrastructureMapper {

    public Exercise toModel(ExerciseEntity entity) {
        return Exercise.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .exerciseStatus(entity.getExerciseStatus())
                .muscleGroup(entity.getMuscleGroup())
                .trainerEmail(entity.getTrainerEmail())
                .build();
    }

    public ExerciseEntity toEntity(Exercise model) {
        return ExerciseEntity.builder()
                .id(model.getId())
                .name(model.getName())
                .description(model.getDescription())
                .exerciseStatus(model.getExerciseStatus())
                .muscleGroup(model.getMuscleGroup())
                .trainerEmail(model.getTrainerEmail())
                .build();
    }
}
