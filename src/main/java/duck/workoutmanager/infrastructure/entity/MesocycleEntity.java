package duck.workoutmanager.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "mesocycle")
public class MesocycleEntity {

    @Id
    @Column(columnDefinition = "UUID")
    private UUID id;
    private String name;
    private LocalDate startDate;
    private LocalDate expectedEndDate;

    @Column(length = 10000)
    private String privateCoachNotes;

    @Column(length = 10000)
    private String publicCoachNotes;

    private int daysPerWeek;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "macrocycle_id", nullable = false)
    private MacrocycleEntity macrocycle;

    @OneToMany(mappedBy = "mesocycle")
    private Set<WeekEntity> weeks;
}
