package duck.workoutmanager.presentation.request.macrocycle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMacrocycleRequest {

    private String userEmail;
    private String name;
    private String expectedEndDate;
}
