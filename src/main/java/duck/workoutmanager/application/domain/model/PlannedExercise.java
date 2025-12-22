package duck.workoutmanager.application.domain.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@Data
@Slf4j
public class PlannedExercise {

    private UUID id;
    private int sets;
    private String timeUnderTension;
    private int restSeconds;
    private String coachesNotes;
    private String userNotes;
    private LocalDate completionDate;

    private Exercise exercise;
    private TrainingDay trainingDay;

    private Set<ExerciseExecution> exerciseExecutions;

    @Builder
    public PlannedExercise(UUID id, int sets, String timeUnderTension, int restSeconds, String coachesNotes, String userNotes, LocalDate completionDate, Exercise exercise, TrainingDay trainingDay) {
        this.id = id;
        this.sets = sets;
        this.timeUnderTension = timeUnderTension;
        this.restSeconds = restSeconds;
        this.coachesNotes = coachesNotes;
        this.userNotes = userNotes;
        this.completionDate = completionDate;
        this.exercise = exercise;
        this.trainingDay = trainingDay;
    }
}
