package duck.workoutmanager.infrastructure.repository;

import duck.workoutmanager.infrastructure.entity.MacrocycleEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MacrocycleJpaRepository extends JpaRepository<MacrocycleEntity, UUID> {

    @Query("SELECT m FROM MacrocycleEntity m WHERE m.id = :macrocycleId")
    @EntityGraph(attributePaths = {"user"})
    Optional<MacrocycleEntity> findByIdWithUser(UUID macrocycleId);

    List<MacrocycleEntity> findAllByUserEmail(String userEmail);
}
