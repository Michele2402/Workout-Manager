package duck.workoutmanager.application.port.in.macrocycle;

import duck.workoutmanager.application.domain.model.Macrocycle;

public interface GetActiveMacrocycleUseCase {
    Macrocycle getActiveMacrocycle();
}
