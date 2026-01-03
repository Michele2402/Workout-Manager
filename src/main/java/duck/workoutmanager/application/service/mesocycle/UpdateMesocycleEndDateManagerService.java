package duck.workoutmanager.application.service.mesocycle;

import duck.workoutmanager.application.command.mesocycle.UpdateMesocycleEndDateCommand;
import duck.workoutmanager.application.domain.exception.ObjectNotFoundException;
import duck.workoutmanager.application.domain.model.Mesocycle;
import duck.workoutmanager.application.domain.model.User;
import duck.workoutmanager.application.port.in.mesocycle.UpdateMesocycleEndDateUseCase;
import duck.workoutmanager.application.port.out.mesocycle.GetMesocyclePortOut;
import duck.workoutmanager.application.port.out.mesocycle.UpdateMesocyclePortOut;
import duck.workoutmanager.application.utils.AuthorizationUtils;
import duck.workoutmanager.application.utils.CheckAttribute;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateMesocycleEndDateManagerService implements UpdateMesocycleEndDateUseCase {

    private final CheckAttribute checkAttribute;
    private final AuthorizationUtils authorizationUtils;

    private final GetMesocyclePortOut getMesocyclePortOut;
    private final UpdateMesocyclePortOut updateMesocyclePortOut;


    @Override
    public LocalDate updateMesocycleEndDate(UpdateMesocycleEndDateCommand command) {

        checkCommand(command);

        Mesocycle mesocycle = getMesocyclePortOut.getByIdWithUser(command.getMesocycleId());

        if (mesocycle == null) {
            log.error("Mesocycle with id ({}) not found", command.getMesocycleId());
            throw new ObjectNotFoundException("Mesocycle not found");
        }

        User user = mesocycle.getMacrocycle().getUser();
        authorizationUtils.checkUserIsAssociatedWithLoggedTrainer(user);

        mesocycle.setExpectedEndDate(command.getExpectedEndDate());

        Mesocycle updatedMesocycle = updateMesocyclePortOut.update(mesocycle);

        return updatedMesocycle.getExpectedEndDate();
    }


    private void checkCommand(UpdateMesocycleEndDateCommand command) {
        checkAttribute.checkDateIsNotInThePast(command.getExpectedEndDate(), "Mesocycle expected end date");
    }
}
