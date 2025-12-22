package duck.workoutmanager.application.domain.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@NoArgsConstructor
@Data
@Slf4j
public class TrainingDay {

    private UUID id;
    private String weekDay;

    private Week week;

    @Builder
    public TrainingDay(UUID id, String weekDay, Week week) {
        this.id = id;
        this.weekDay = weekDay;
        this.week = week;
    }
}
