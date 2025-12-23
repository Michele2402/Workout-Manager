package duck.workoutmanager.presentation.response.exercise;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExerciseResponse {

    private String id;
    private String name;
    private String description;
    private String muscleGroup;
    private String exerciseStatus;
    private String trainerEmail;
}
