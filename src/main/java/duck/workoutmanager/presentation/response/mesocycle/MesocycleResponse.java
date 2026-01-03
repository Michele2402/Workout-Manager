package duck.workoutmanager.presentation.response.mesocycle;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MesocycleResponse {
    private String id;
    private String name;
    private String startDate;
    private String expectedEndDate;
    private String privateCoachNotes;
    private String publicCoachNotes;
    private int daysPerWeek;
}
