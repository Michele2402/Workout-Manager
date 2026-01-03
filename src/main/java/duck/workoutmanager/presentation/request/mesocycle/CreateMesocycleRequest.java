package duck.workoutmanager.presentation.request.mesocycle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMesocycleRequest {

    private String name;
    private String expectedEndDate;
    private int daysPerWeek;
    private int totalWeeks;
    private String macrocycleId;
}
