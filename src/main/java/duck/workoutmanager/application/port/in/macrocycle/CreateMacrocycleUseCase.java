package duck.workoutmanager.application.port.in.macrocycle;

import duck.workoutmanager.application.command.macrocycle.CreateMacrocycleCommand;
import duck.workoutmanager.application.domain.model.Macrocycle;

public interface CreateMacrocycleUseCase {

    Macrocycle createMacrocycle(CreateMacrocycleCommand command);
}
