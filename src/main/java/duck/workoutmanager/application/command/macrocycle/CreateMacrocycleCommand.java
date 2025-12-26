package duck.workoutmanager.application.command.macrocycle;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateMacrocycleCommand {

    private String userEmail;
    private String name;
    private LocalDate expectedEndDate;
}
