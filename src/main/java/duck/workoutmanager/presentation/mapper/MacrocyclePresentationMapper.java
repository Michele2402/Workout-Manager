package duck.workoutmanager.presentation.mapper;

import duck.workoutmanager.application.command.macrocycle.*;
import duck.workoutmanager.application.domain.model.Macrocycle;
import duck.workoutmanager.application.utils.ParseAttributes;
import duck.workoutmanager.presentation.request.macrocycle.*;
import duck.workoutmanager.presentation.response.macrocycle.MacrocycleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MacrocyclePresentationMapper {

    private final ParseAttributes parseAttributes;

    public CreateMacrocycleCommand toCommand(CreateMacrocycleRequest request) {
        return CreateMacrocycleCommand.builder()
                .userEmail(request.getUserEmail())
                .name(request.getName())
                .expectedEndDate(parseAttributes.parseLocalDate(request.getExpectedEndDate()))
                .build();
    }

    public ActivateMacrocycleCommand toCommand(ActivateMacrocycleRequest request) {
        return ActivateMacrocycleCommand.builder()
                .userEmail(request.getUserEmail())
                .macrocycleId(parseAttributes.parseUUID(request.getMacrocycleId()))
                .build();
    }

    public UpdateMacrocycleNotesCommand toCommand(UpdateMacrocycleNotesRequest request) {
        return UpdateMacrocycleNotesCommand.builder()
                .macrocycleId(parseAttributes.parseUUID(request.getMacrocycleId()))
                .coachNotes(request.getCoachNotes())
                .build();
    }

    public UpdateMacrocycleNameCommand toCommand(UpdateMacrocycleNameRequest request) {
        return UpdateMacrocycleNameCommand.builder()
                .macrocycleId(parseAttributes.parseUUID(request.getMacrocycleId()))
                .name(request.getName())
                .build();
    }

    public UpdateMacrocycleEndDateCommand toCommand(UpdateMacrocycleEndDateRequest request) {
        return UpdateMacrocycleEndDateCommand.builder()
                .macrocycleId(parseAttributes.parseUUID(request.getMacrocycleId()))
                .expectedEndDate(parseAttributes.parseLocalDate(request.getExpectedEndDate()))
                .build();
    }


    public MacrocycleResponse toResponse(Macrocycle macrocycle) {
        return MacrocycleResponse.builder()
                .id(macrocycle.getId().toString())
                .name(macrocycle.getName())
                .startDate(macrocycle.getStartDate().toString())
                .expectedEndDate(macrocycle.getExpectedEndDate().toString())
                .status(macrocycle.getStatus().toString())
                .coachNotes(macrocycle.getCoachNotes())
                .build();
    }
}
