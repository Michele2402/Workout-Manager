package duck.workoutmanager.application.command.exercise;

import duck.workoutmanager.application.domain.enums.ExerciseStatusEnum;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateExerciseStatusCommand {

    private UUID exerciseId;
    private ExerciseStatusEnum exerciseStatus;
}
