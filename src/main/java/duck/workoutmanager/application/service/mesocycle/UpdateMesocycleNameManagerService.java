package duck.workoutmanager.application.service.mesocycle;

import duck.workoutmanager.application.command.mesocycle.UpdateMesocycleNameCommand;
import duck.workoutmanager.application.domain.exception.AlreadyExistsException;
import duck.workoutmanager.application.domain.exception.ObjectNotFoundException;
import duck.workoutmanager.application.domain.model.Mesocycle;
import duck.workoutmanager.application.domain.model.User;
import duck.workoutmanager.application.port.in.mesocycle.UpdateMesocycleNameUseCase;
import duck.workoutmanager.application.port.out.mesocycle.GetMesocyclePortOut;
import duck.workoutmanager.application.port.out.mesocycle.UpdateMesocyclePortOut;
import duck.workoutmanager.application.port.out.user.CheckUserPortOut;
import duck.workoutmanager.application.utils.AuthorizationUtils;
import duck.workoutmanager.application.utils.CheckAttribute;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateMesocycleNameManagerService implements UpdateMesocycleNameUseCase {

    private final CheckAttribute checkAttribute;
    private final AuthorizationUtils authorizationUtils;

    private final GetMesocyclePortOut getMesocyclePortOut;
    private final UpdateMesocyclePortOut updateMesocyclePortOut;
    private final CheckUserPortOut checkUserPortOut;

    @Override
    public String updateName(UpdateMesocycleNameCommand command) {

        checkCommand(command);

        Mesocycle mesocycle = getMesocyclePortOut.getByIdWithUser(command.getMesocycleId());

        if (mesocycle == null) {
            log.error("Mesocycle with id ({}) not found", command.getMesocycleId());
            throw new ObjectNotFoundException("Mesocycle not found");
        }

        User user = mesocycle.getMacrocycle().getUser();
        authorizationUtils.checkUserIsAssociatedWithLoggedTrainer(user);

        boolean hasAnyMesocycleWithSameName = checkUserPortOut.checkHasAnyMesocycleWithSameName(
                mesocycle.getMacrocycle().getId(),
                command.getName()
        );

        boolean isNameSameAsCurrent = mesocycle.getName().equalsIgnoreCase(command.getName());

        if (hasAnyMesocycleWithSameName && !isNameSameAsCurrent) {
            log.error("Mesocycle with name ({}) already exists for user ({})", command.getName(), user.getEmail());
            throw new AlreadyExistsException("Mesocycle with the same name already exists for this user");
        }

        mesocycle.setName(command.getName());

        Mesocycle updatedMesocycle = updateMesocyclePortOut.update(mesocycle);

        return updatedMesocycle.getName();
    }


    private void checkCommand(UpdateMesocycleNameCommand command) {
        checkAttribute.checkStringIsNotNullOrEmpty(command.getName(), "Mesocycle name");
        checkAttribute.checkStringIsShorterThan(command.getName(), 100, "Mesocycle name");
    }

}
