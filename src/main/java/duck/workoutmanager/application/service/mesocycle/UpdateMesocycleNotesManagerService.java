package duck.workoutmanager.application.service.mesocycle;

import duck.workoutmanager.application.command.mesocycle.UpdateMesocycleNotesCommand;
import duck.workoutmanager.application.domain.exception.ObjectNotFoundException;
import duck.workoutmanager.application.domain.model.Macrocycle;
import duck.workoutmanager.application.domain.model.Mesocycle;
import duck.workoutmanager.application.domain.model.User;
import duck.workoutmanager.application.port.in.mesocycle.UpdateMesocycleNotesUseCase;
import duck.workoutmanager.application.port.out.mesocycle.GetMesocyclePortOut;
import duck.workoutmanager.application.port.out.mesocycle.UpdateMesocyclePortOut;
import duck.workoutmanager.application.utils.AuthorizationUtils;
import duck.workoutmanager.application.utils.CheckAttribute;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateMesocycleNotesManagerService implements UpdateMesocycleNotesUseCase {

    private final CheckAttribute checkAttribute;
    private final AuthorizationUtils authorizationUtils;

    private final GetMesocyclePortOut getMesocyclePortOut;
    private final UpdateMesocyclePortOut updateMesocyclePortOut;


    @Override
    public String updateMesocycleNotes(UpdateMesocycleNotesCommand command) {

        checkCommand(command);

        Mesocycle mesocycle = getMesocyclePortOut.getByIdWithUser(command.getMesocycleId());

        if (mesocycle == null) {
            log.error("Mesocycle with id ({}) not found", command.getMesocycleId());
            throw new ObjectNotFoundException("Mesocycle not found");
        }

        User user = mesocycle.getMacrocycle().getUser();
        authorizationUtils.checkUserIsAssociatedWithLoggedTrainer(user);


        if(command.isNotesPrivate()) {
            mesocycle.setPrivateCoachNotes(command.getCoachNotes());
        } else {
            mesocycle.setPublicCoachNotes(command.getCoachNotes());
        }

        Mesocycle updatedMesocycle = updateMesocyclePortOut.update(mesocycle);

        return command.isNotesPrivate() ? updatedMesocycle.getPrivateCoachNotes() : updatedMesocycle.getPublicCoachNotes();
    }


    private void checkCommand(UpdateMesocycleNotesCommand command) {
        checkAttribute.checkStringIsShorterThan(command.getCoachNotes(), 10000, "Coach notes");
    }
}
