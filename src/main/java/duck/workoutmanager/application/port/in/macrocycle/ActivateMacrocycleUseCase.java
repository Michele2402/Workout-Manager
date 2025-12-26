package duck.workoutmanager.application.port.in.macrocycle;

import duck.workoutmanager.application.command.macrocycle.ActivateMacrocycleCommand;
import duck.workoutmanager.application.domain.model.Macrocycle;

import java.util.List;

public interface ActivateMacrocycleUseCase {

    List<Macrocycle> activateMacrocycle(ActivateMacrocycleCommand command);
}
