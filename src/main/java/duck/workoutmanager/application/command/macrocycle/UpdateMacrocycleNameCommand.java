package duck.workoutmanager.application.command.macrocycle;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateMacrocycleNameCommand {
    private UUID macrocycleId;
    private String name;
}
