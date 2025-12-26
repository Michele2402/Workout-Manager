package duck.workoutmanager.application.command.macrocycle;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivateMacrocycleCommand {
    private String userEmail;
    private UUID macrocycleId;
}
