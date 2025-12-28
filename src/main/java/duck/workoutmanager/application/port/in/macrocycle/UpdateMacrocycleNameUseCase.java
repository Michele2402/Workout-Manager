package duck.workoutmanager.application.port.in.macrocycle;

import duck.workoutmanager.application.command.macrocycle.UpdateMacrocycleNameCommand;

public interface UpdateMacrocycleNameUseCase {
    String updateMacrocycleName(UpdateMacrocycleNameCommand command);
}
