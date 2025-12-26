package duck.workoutmanager.presentation.request.macrocycle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActivateMacrocycleRequest {

    private String userEmail;
    private String macrocycleId;
}
