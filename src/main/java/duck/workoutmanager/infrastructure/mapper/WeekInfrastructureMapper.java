package duck.workoutmanager.infrastructure.mapper;

import duck.workoutmanager.application.domain.model.Week;
import duck.workoutmanager.infrastructure.entity.MesocycleEntity;
import duck.workoutmanager.infrastructure.entity.WeekEntity;
import org.springframework.stereotype.Component;

@Component
public class WeekInfrastructureMapper {

    public WeekEntity toEntityWithMesocycleId(Week week) {

        MesocycleEntity mesocycleEntity = MesocycleEntity.builder()
                .id(week.getMesocycle().getId())
                .build();

        return WeekEntity.builder()
                .id(week.getId())
                .weekNumber(week.getWeekNumber())
                .mesocycle(mesocycleEntity)
                .build();
    }
}
