package duck.workoutmanager.presentation.mapper;

import duck.workoutmanager.application.command.macrocycle.ActivateMacrocycleCommand;
import duck.workoutmanager.application.command.macrocycle.CreateMacrocycleCommand;
import duck.workoutmanager.application.domain.model.Macrocycle;
import duck.workoutmanager.application.utils.ParseAttributes;
import duck.workoutmanager.presentation.request.macrocycle.ActivateMacrocycleRequest;
import duck.workoutmanager.presentation.request.macrocycle.CreateMacrocycleRequest;
import duck.workoutmanager.presentation.response.macrocycle.MacrocycleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MacrocyclePresentationMapper {

    private final ParseAttributes parseAttributes;

    public CreateMacrocycleCommand toCommand (CreateMacrocycleRequest request) {
        return CreateMacrocycleCommand.builder()
                .userEmail(request.getUserEmail())
                .name(request.getName())
                .expectedEndDate(parseAttributes.parseLocalDate(request.getExpectedEndDate()))
                .build();
    }

    public ActivateMacrocycleCommand toCommand (ActivateMacrocycleRequest request) {
        return ActivateMacrocycleCommand.builder()
                .userEmail(request.getUserEmail())
                .macrocycleId(parseAttributes.parseUUID(request.getMacrocycleId()))
                .build();
    }


    public MacrocycleResponse toResponse (Macrocycle macrocycle) {
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
