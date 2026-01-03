package duck.workoutmanager.application.command.mesocycle;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateMesocycleEndDateCommand {
    private UUID mesocycleId;
    private LocalDate expectedEndDate;
}
