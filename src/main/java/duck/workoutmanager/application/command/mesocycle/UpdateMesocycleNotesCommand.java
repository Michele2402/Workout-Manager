package duck.workoutmanager.application.command.mesocycle;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UpdateMesocycleNotesCommand {
    private UUID mesocycleId;
    private String coachNotes;
    private boolean notesPrivate;
}
