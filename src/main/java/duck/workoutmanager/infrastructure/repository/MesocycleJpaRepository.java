package duck.workoutmanager.infrastructure.repository;

import duck.workoutmanager.infrastructure.entity.MesocycleEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface MesocycleJpaRepository extends JpaRepository<MesocycleEntity, UUID> {

    boolean existsByNameAndMacrocycleId(String name, UUID macrocycleId);


    @Query("SELECT m FROM MesocycleEntity m " +
           "WHERE m.id = :mesocycleId")
    @EntityGraph(attributePaths = {"macrocycle", "macrocycle.user"})
    Optional<MesocycleEntity> findByIdWithUser(UUID mesocycleId);
}
