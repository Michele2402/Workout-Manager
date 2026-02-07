package duck.workoutmanager.presentation.response.macrocycle;

import duck.workoutmanager.presentation.response.mesocycle.MesocycleResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetActiveMacrocycleResponse {

    private String id;
    private String name;
    private String startDate;
    private String expectedEndDate;
    private String status;
    private String coachNotes;
    private List<MesocycleResponse> mesocycles;
}
