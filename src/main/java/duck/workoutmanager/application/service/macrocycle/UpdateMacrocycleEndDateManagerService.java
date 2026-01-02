package duck.workoutmanager.application.service.macrocycle;

import duck.workoutmanager.application.command.macrocycle.UpdateMacrocycleEndDateCommand;
import duck.workoutmanager.application.domain.exception.ObjectNotFoundException;
import duck.workoutmanager.application.domain.model.Macrocycle;
import duck.workoutmanager.application.domain.model.User;
import duck.workoutmanager.application.port.in.macrocycle.UpdateMacrocycleEndDateUseCase;
import duck.workoutmanager.application.port.out.macrocycle.GetMacrocyclePortOut;
import duck.workoutmanager.application.port.out.macrocycle.UpdateMacrocyclePortOut;
import duck.workoutmanager.application.utils.AuthorizationUtils;
import duck.workoutmanager.application.utils.CheckAttribute;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateMacrocycleEndDateManagerService implements UpdateMacrocycleEndDateUseCase {

    private final CheckAttribute checkAttribute;
    private final AuthorizationUtils authorizationUtils;

    private final GetMacrocyclePortOut getMacrocyclePortOut;
    private final UpdateMacrocyclePortOut updateMacrocyclePortOut;


    @Override
    public LocalDate updateMacrocycleEndDate(UpdateMacrocycleEndDateCommand command) {
        checkCommand(command);

        Macrocycle macrocycle = getMacrocyclePortOut.getByIdWithUser(command.getMacrocycleId());

        if (macrocycle == null) {
            log.error("Macrocycle with id ({}) not found", command.getMacrocycleId());
            throw new ObjectNotFoundException("Macrocycle not found");
        }

        User user = macrocycle.getUser();
        authorizationUtils.checkUserIsAssociatedWithLoggedTrainer(user);

        macrocycle.setExpectedEndDate(command.getExpectedEndDate());

        Macrocycle updatedMacrocycle = updateMacrocyclePortOut.update(macrocycle);

        return updatedMacrocycle.getExpectedEndDate();
    }


    private void checkCommand(UpdateMacrocycleEndDateCommand command) {
        checkAttribute.checkDateIsNotInThePast(command.getExpectedEndDate(), "Expected end date");
    }
}
