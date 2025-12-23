package duck.workoutmanager.presentation.request.exercise;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateExerciseRequest {

    private String name;
    private String description;
    private String muscleGroup;
}
