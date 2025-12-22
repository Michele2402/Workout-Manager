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
public class Macrocycle {

    private UUID id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String coachNotes;

    private User user;

    private Set<Mesocycle> mesocycles;

    @Builder
    public Macrocycle(UUID id, String name, LocalDate startDate, LocalDate endDate, String coachNotes, User user) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.coachNotes = coachNotes;
        this.user = user;
    }
}
