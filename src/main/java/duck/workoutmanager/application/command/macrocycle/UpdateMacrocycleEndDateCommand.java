package duck.workoutmanager.application.command.macrocycle;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateMacrocycleEndDateCommand {
    private UUID macrocycleId;
    private LocalDate expectedEndDate;
}
