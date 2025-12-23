package duck.workoutmanager.infrastructure.repository;

import duck.workoutmanager.infrastructure.entity.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ExerciseJpaRepository extends JpaRepository<ExerciseEntity, UUID> {

    Optional<ExerciseEntity> getExerciseByName(String name);

    List<ExerciseEntity> getAllByTrainerEmail(String email);
}
