package duck.workoutmanager.presentation.mapper;

import duck.workoutmanager.application.command.mesocycle.CreateMesocycleCommand;
import duck.workoutmanager.application.command.mesocycle.UpdateMesocycleEndDateCommand;
import duck.workoutmanager.application.command.mesocycle.UpdateMesocycleNotesCommand;
import duck.workoutmanager.application.domain.model.Mesocycle;
import duck.workoutmanager.application.utils.ParseAttributes;
import duck.workoutmanager.presentation.request.mesocycle.CreateMesocycleRequest;
import duck.workoutmanager.presentation.request.mesocycle.UpdateMesocycleEndDateRequest;
import duck.workoutmanager.presentation.request.mesocycle.UpdateMesocycleNotesRequest;
import duck.workoutmanager.presentation.response.mesocycle.MesocycleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MesocyclePresentationMapper {

    private final ParseAttributes parseAttributes;

    public CreateMesocycleCommand toCommand(CreateMesocycleRequest request) {
        return CreateMesocycleCommand.builder()
                .name(request.getName())
                .expectedEndDate(parseAttributes.parseLocalDate(request.getExpectedEndDate()))
                .daysPerWeek(request.getDaysPerWeek())
                .totalWeeks(request.getTotalWeeks())
                .macrocycleId(parseAttributes.parseUUID(request.getMacrocycleId()))
                .build();
    }

    public UpdateMesocycleNotesCommand toCommand(UpdateMesocycleNotesRequest request) {
        return UpdateMesocycleNotesCommand.builder()
                .mesocycleId(parseAttributes.parseUUID(request.getMesocycleId()))
                .coachNotes(request.getCoachNotes())
                .notesPrivate(request.isNotesPrivate())
                .build();
    }

    public UpdateMesocycleEndDateCommand toCommand(UpdateMesocycleEndDateRequest request) {
        return UpdateMesocycleEndDateCommand.builder()
                .mesocycleId(parseAttributes.parseUUID(request.getMesocycleId()))
                .expectedEndDate(parseAttributes.parseLocalDate(request.getExpectedEndDate()))
                .build();
    }


    public MesocycleResponse toResponse(Mesocycle mesocycle) {
        return MesocycleResponse.builder()
                .id(mesocycle.getId().toString())
                .name(mesocycle.getName())
                .startDate(mesocycle.getStartDate().toString())
                .expectedEndDate(mesocycle.getExpectedEndDate().toString())
                .privateCoachNotes(mesocycle.getPrivateCoachNotes())
                .publicCoachNotes(mesocycle.getPublicCoachNotes())
                .daysPerWeek(mesocycle.getDaysPerWeek())
                .build();
    }
}
