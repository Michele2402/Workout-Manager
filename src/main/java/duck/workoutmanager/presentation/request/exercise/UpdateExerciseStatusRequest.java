package duck.workoutmanager.presentation.request.exercise;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateExerciseStatusRequest {

    private String exerciseId;
    private String exerciseStatus;
}
