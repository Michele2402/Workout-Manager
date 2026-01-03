package duck.workoutmanager.application.port.in.mesocycle;

import duck.workoutmanager.application.command.mesocycle.UpdateMesocycleEndDateCommand;

import java.time.LocalDate;

public interface UpdateMesocycleEndDateUseCase {

    LocalDate updateMesocycleEndDate(UpdateMesocycleEndDateCommand command);
}
