package duck.workoutmanager.application.port.in.mesocycle;

import duck.workoutmanager.application.command.mesocycle.CreateMesocycleCommand;
import duck.workoutmanager.application.domain.model.Mesocycle;

public interface CreateMesocycleUseCase {

    Mesocycle create(CreateMesocycleCommand command);
}
