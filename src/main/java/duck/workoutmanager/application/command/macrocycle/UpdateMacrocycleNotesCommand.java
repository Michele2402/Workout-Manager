package duck.workoutmanager.application.command.macrocycle;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UpdateMacrocycleNotesCommand {
    private UUID macrocycleId;
    private String coachNotes;
}
