package duck.workoutmanager.application.service.macrocycle;

import duck.workoutmanager.application.command.macrocycle.UpdateMacrocycleNotesCommand;
import duck.workoutmanager.application.domain.exception.ObjectNotFoundException;
import duck.workoutmanager.application.domain.model.Macrocycle;
import duck.workoutmanager.application.domain.model.User;
import duck.workoutmanager.application.port.in.macrocycle.UpdateMacrocycleNotesUseCase;
import duck.workoutmanager.application.port.out.macrocycle.GetMacrocyclePortOut;
import duck.workoutmanager.application.port.out.macrocycle.UpdateMacrocyclePortOut;
import duck.workoutmanager.application.utils.AuthorizationUtils;
import duck.workoutmanager.application.utils.CheckAttribute;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateMacrocycleNotesManagerService implements UpdateMacrocycleNotesUseCase {

    private final CheckAttribute checkAttribute;
    private final AuthorizationUtils authorizationUtils;

    private final GetMacrocyclePortOut getMacrocyclePortOut;
    private final UpdateMacrocyclePortOut updateMacrocyclePortOut;


    @Override
    public String updateMacrocycleNotes(UpdateMacrocycleNotesCommand command) {

        checkCommand(command);

        Macrocycle macrocycle = getMacrocyclePortOut.getByIdWithUser(command.getMacrocycleId());

        if (macrocycle == null) {
            log.error("Macrocycle with id ({}) not found", command.getMacrocycleId());
            throw new ObjectNotFoundException("Macrocycle not found");
        }

        User user = macrocycle.getUser();
        authorizationUtils.checkUserIsAssociatedWithLoggedTrainer(user);

        macrocycle.setCoachNotes(command.getCoachNotes());

        Macrocycle updatedMacrocycle = updateMacrocyclePortOut.update(macrocycle);

        return updatedMacrocycle.getCoachNotes();
    }


    private void checkCommand(UpdateMacrocycleNotesCommand command) {
        checkAttribute.checkStringIsShorterThan(command.getCoachNotes(), 10000, "Coach notes");
    }
}
