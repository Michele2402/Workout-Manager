package duck.workoutmanager.application.domain.model;

import duck.workoutmanager.application.domain.enums.MacrocycleStatusEnum;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Slf4j
public class Macrocycle {

    private UUID id;
    private String name;
    private LocalDate startDate;
    private LocalDate expectedEndDate;
    private String coachNotes;
    private MacrocycleStatusEnum status;

    private User user;

    private Set<Mesocycle> mesocycles;

    @Builder
    public Macrocycle(UUID id, String name, LocalDate startDate, LocalDate expectedEndDate, String coachNotes, MacrocycleStatusEnum status, User user) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.expectedEndDate = expectedEndDate;
        this.coachNotes = coachNotes;
        this.status = status;
        this.user = user;
    }


    public void activate() {
        this.status = MacrocycleStatusEnum.ACTIVE;
    }

    public void complete() {
        this.status = MacrocycleStatusEnum.COMPLETED;
    }
}
