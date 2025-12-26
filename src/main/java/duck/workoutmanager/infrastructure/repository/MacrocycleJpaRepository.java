package duck.workoutmanager.infrastructure.repository;

import duck.workoutmanager.infrastructure.entity.MacrocycleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MacrocycleJpaRepository extends JpaRepository<MacrocycleEntity, UUID> {

    List<MacrocycleEntity> findAllByUserEmail(String userEmail);
}
