package duck.workoutmanager.infrastructure.entity;

import duck.workoutmanager.application.domain.enums.MuscleGroupEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "exercise")
public class ExerciseEntity {

    @Id
    @Column(columnDefinition = "UUID")
    private UUID id;
    private String name;

    @Column(length = 10000)
    private String description;

    private MuscleGroupEnum muscleGroup;

    private String trainerEmail;

    @OneToMany(mappedBy = "exercise")
    private Set<PlannedExerciseEntity> plannedExercises;
}
