package duck.workoutmanager.application.port.in.macrocycle;

import duck.workoutmanager.application.command.macrocycle.UpdateMacrocycleEndDateCommand;

import java.time.LocalDate;

public interface UpdateMacrocycleEndDateUseCase {
    LocalDate updateMacrocycleEndDate(UpdateMacrocycleEndDateCommand command);
}
