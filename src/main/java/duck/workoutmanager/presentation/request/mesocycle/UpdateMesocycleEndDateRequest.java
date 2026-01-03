package duck.workoutmanager.presentation.request.mesocycle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMesocycleEndDateRequest {
    private String mesocycleId;
    private String expectedEndDate;
}
