package duck.workoutmanager.application.command.exercise;

import duck.workoutmanager.application.domain.enums.MuscleGroupEnum;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateExerciseCommand {

    private String name;
    private String description;
    private MuscleGroupEnum muscleGroup;
}
