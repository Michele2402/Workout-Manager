package duck.workoutmanager.infrastructure.mapper;

import duck.workoutmanager.application.domain.model.Macrocycle;
import duck.workoutmanager.infrastructure.entity.MacrocycleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MacrocycleInfrastructureMapper {

    private final UserInfrastructureMapper userMapper;

    public MacrocycleEntity toEntityWithUserEmail(Macrocycle macrocycle) {
        return MacrocycleEntity.builder()
                .id(macrocycle.getId())
                .name(macrocycle.getName())
                .startDate(macrocycle.getStartDate())
                .expectedEndDate(macrocycle.getExpectedEndDate())
                .status(macrocycle.getStatus())
                .coachNotes(macrocycle.getCoachNotes())
                .user(userMapper.toEntityWithOnlyEmail(macrocycle.getUser()))
                .build();
    }


    public Macrocycle toModelWithNoDependencies(MacrocycleEntity entity) {
        return Macrocycle.builder()
                .id(entity.getId())
                .name(entity.getName())
                .startDate(entity.getStartDate())
                .expectedEndDate(entity.getExpectedEndDate())
                .status(entity.getStatus())
                .coachNotes(entity.getCoachNotes())
                .build();
    }
}
