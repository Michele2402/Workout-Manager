package duck.workoutmanager.application.port.in.mesocycle;

import duck.workoutmanager.application.command.mesocycle.UpdateMesocycleNameCommand;

public interface UpdateMesocycleNameUseCase {
    String updateName(UpdateMesocycleNameCommand command);
}
