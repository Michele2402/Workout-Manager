package duck.workoutmanager.application.command.exercise;

import duck.workoutmanager.application.domain.enums.MuscleGroupEnum;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateExerciseCommand {

    private UUID exerciseId;
    private String name;
    private String description;
    private MuscleGroupEnum muscleGroup;
}
