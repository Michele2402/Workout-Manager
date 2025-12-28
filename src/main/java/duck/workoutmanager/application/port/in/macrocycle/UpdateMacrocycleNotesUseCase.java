package duck.workoutmanager.application.port.in.macrocycle;

import duck.workoutmanager.application.command.macrocycle.UpdateMacrocycleNotesCommand;

public interface UpdateMacrocycleNotesUseCase {
    String updateMacrocycleNotes(UpdateMacrocycleNotesCommand command);
}
