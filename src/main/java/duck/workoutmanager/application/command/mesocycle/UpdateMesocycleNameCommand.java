package duck.workoutmanager.application.command.mesocycle;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateMesocycleNameCommand {
    private UUID mesocycleId;
    private String name;
}
