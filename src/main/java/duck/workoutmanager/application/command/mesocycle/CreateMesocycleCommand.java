package duck.workoutmanager.application.command.mesocycle;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateMesocycleCommand {

    private String name;
    private LocalDate expectedEndDate;
    private int daysPerWeek;
    private int totalWeeks;
    private UUID macrocycleId;
}
