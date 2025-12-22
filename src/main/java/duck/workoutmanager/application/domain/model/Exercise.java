package duck.workoutmanager.application.domain.model;

import duck.workoutmanager.application.domain.enums.MuscleGroupEnum;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@NoArgsConstructor
@Data
@Slf4j
public class Exercise {

    private UUID id;
    private String name;
    private String description;
    private MuscleGroupEnum muscleGroup;
    private String trainerEmail;

    @Builder
    public Exercise(UUID id, String name, String description, MuscleGroupEnum muscleGroup, String trainerEmail) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.muscleGroup = muscleGroup;
        this.trainerEmail = trainerEmail;
    }
}
