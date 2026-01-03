package duck.workoutmanager.application.port.in.mesocycle;

import duck.workoutmanager.application.command.mesocycle.UpdateMesocycleNotesCommand;

public interface UpdateMesocycleNotesUseCase {
    String updateMesocycleNotes(UpdateMesocycleNotesCommand command);
}
