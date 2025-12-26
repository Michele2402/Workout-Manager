package duck.workoutmanager.presentation.response.macrocycle;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MacrocycleResponse {

    private String id;
    private String name;
    private String startDate;
    private String expectedEndDate;
    private String status;
    private String coachNotes;
}
