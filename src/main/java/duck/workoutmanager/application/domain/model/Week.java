package duck.workoutmanager.application.domain.model;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Slf4j
public class Week {

    private UUID id;
    private int weekNumber;

    private Mesocycle mesocycle;

    private Set<TrainingDay> trainingDays;

    @Builder
    public Week(UUID id, int weekNumber, Mesocycle mesocycle) {
        this.id = id;
        this.weekNumber = weekNumber;
        this.mesocycle = mesocycle;
    }
}
