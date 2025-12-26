package duck.workoutmanager.infrastructure.repository;

import duck.workoutmanager.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByEmail(String email);

    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END " +
           "FROM MacrocycleEntity m " +
           "JOIN m.user u " +
           "WHERE u.email = :email")
    boolean hasAnyMacrocycle(String email);
}
