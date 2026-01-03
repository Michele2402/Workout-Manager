package duck.workoutmanager.infrastructure.mapper;

import duck.workoutmanager.application.domain.model.Macrocycle;
import duck.workoutmanager.application.domain.model.Mesocycle;
import duck.workoutmanager.application.domain.model.User;
import duck.workoutmanager.infrastructure.entity.MacrocycleEntity;
import duck.workoutmanager.infrastructure.entity.MesocycleEntity;
import duck.workoutmanager.infrastructure.entity.WeekEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MesocycleInfrastructureMapper {

    private final WeekInfrastructureMapper weekMapper;

    public MesocycleEntity toEntityWithWeeksAndMacrocycleId(Mesocycle mesocycle) {

        Set<WeekEntity> weeks = mesocycle.getWeeks().stream()
                .map(weekMapper::toEntityWithMesocycleId)
                .collect(Collectors.toSet());

        MacrocycleEntity macrocycle = MacrocycleEntity.builder()
                .id(mesocycle.getMacrocycle().getId())
                .build();

        return MesocycleEntity.builder()
                .id(mesocycle.getId())
                .name(mesocycle.getName())
                .startDate(mesocycle.getStartDate())
                .expectedEndDate(mesocycle.getExpectedEndDate())
                .daysPerWeek(mesocycle.getDaysPerWeek())
                .privateCoachNotes(mesocycle.getPrivateCoachNotes())
                .publicCoachNotes(mesocycle.getPublicCoachNotes())
                .weeks(weeks)
                .macrocycle(macrocycle)
                .build();
    }

    public MesocycleEntity toEntityWithMacrocycleId(Mesocycle mesocycle) {

        MacrocycleEntity macrocycle = MacrocycleEntity.builder()
                .id(mesocycle.getMacrocycle().getId())
                .build();

        return MesocycleEntity.builder()
                .id(mesocycle.getId())
                .name(mesocycle.getName())
                .startDate(mesocycle.getStartDate())
                .expectedEndDate(mesocycle.getExpectedEndDate())
                .daysPerWeek(mesocycle.getDaysPerWeek())
                .privateCoachNotes(mesocycle.getPrivateCoachNotes())
                .publicCoachNotes(mesocycle.getPublicCoachNotes())
                .macrocycle(macrocycle)
                .build();
    }


    public Mesocycle toModel(MesocycleEntity mesocycleEntity) {
        return Mesocycle.builder()
                .id(mesocycleEntity.getId())
                .name(mesocycleEntity.getName())
                .startDate(mesocycleEntity.getStartDate())
                .expectedEndDate(mesocycleEntity.getExpectedEndDate())
                .daysPerWeek(mesocycleEntity.getDaysPerWeek())
                .privateCoachNotes(mesocycleEntity.getPrivateCoachNotes())
                .publicCoachNotes(mesocycleEntity.getPublicCoachNotes())
                .build();
    }

    public Mesocycle toModelWithUser(MesocycleEntity mesocycleEntity) {

        User user = User.builder()
                .email(mesocycleEntity.getMacrocycle().getUser().getEmail())
                .name(mesocycleEntity.getMacrocycle().getUser().getName())
                .surname(mesocycleEntity.getMacrocycle().getUser().getSurname())
                .trainerEmail(mesocycleEntity.getMacrocycle().getUser().getTrainerEmail())
                .role(mesocycleEntity.getMacrocycle().getUser().getRole())
                .build();

        Macrocycle macrocycle = Macrocycle.builder()
                .id(mesocycleEntity.getMacrocycle().getId())
                .name(mesocycleEntity.getMacrocycle().getName())
                .user(user)
                .build();

        return Mesocycle.builder()
                .id(mesocycleEntity.getId())
                .name(mesocycleEntity.getName())
                .startDate(mesocycleEntity.getStartDate())
                .expectedEndDate(mesocycleEntity.getExpectedEndDate())
                .daysPerWeek(mesocycleEntity.getDaysPerWeek())
                .privateCoachNotes(mesocycleEntity.getPrivateCoachNotes())
                .publicCoachNotes(mesocycleEntity.getPublicCoachNotes())
                .macrocycle(macrocycle)
                .build();
    }
}
