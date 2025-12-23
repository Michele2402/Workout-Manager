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
public class Mesocycle {

    private UUID id;
    private String name;
    private LocalDate startDate;
    private LocalDate expectedEndDate;
    private String privateCoachNotes;
    private String publicCoachNotes;
    private int daysPerWeek;

    private Macrocycle macrocycle;

    private Set<Week> weeks;

    @Builder
    public Mesocycle(UUID id, String name, LocalDate startDate, LocalDate expectedEndDate, String privateCoachNotes, String publicCoachNotes, int daysPerWeek, Macrocycle macrocycle) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.expectedEndDate = expectedEndDate;
        this.privateCoachNotes = privateCoachNotes;
        this.publicCoachNotes = publicCoachNotes;
        this.daysPerWeek = daysPerWeek;
        this.macrocycle = macrocycle;
    }
}
