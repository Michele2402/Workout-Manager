package duck.workoutmanager.application.service.macrocycle;

import duck.workoutmanager.application.command.macrocycle.ActivateMacrocycleCommand;
import duck.workoutmanager.application.domain.enums.MacrocycleStatusEnum;
import duck.workoutmanager.application.domain.exception.AuthorizationException;
import duck.workoutmanager.application.domain.exception.ObjectNotFoundException;
import duck.workoutmanager.application.domain.model.Macrocycle;
import duck.workoutmanager.application.domain.model.User;
import duck.workoutmanager.application.port.in.macrocycle.ActivateMacrocycleUseCase;
import duck.workoutmanager.application.port.out.macrocycle.GetMacrocyclePortOut;
import duck.workoutmanager.application.port.out.macrocycle.UpdateMacrocyclePortOut;
import duck.workoutmanager.application.port.out.user.GetUserPortOut;
import duck.workoutmanager.application.utils.AuthorizationUtils;
import duck.workoutmanager.application.utils.CheckAttribute;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ActivateMacrocycleManagerService implements ActivateMacrocycleUseCase {

    private final AuthorizationUtils authorizationUtils;
    private final CheckAttribute checkAttribute;

    private final GetUserPortOut getUserPortOut;
    private final GetMacrocyclePortOut getMacrocyclePortOut;
    private final UpdateMacrocyclePortOut updateMacrocyclePortOut;


    @Override
    public List<Macrocycle> activateMacrocycle(ActivateMacrocycleCommand command) {

        checkCommand(command);

        User user = getUserPortOut.getByEmail(command.getUserEmail());

        if (user == null) {
            log.error("User with email ({}) not found", command.getUserEmail());
            throw new ObjectNotFoundException("User not found");
        }

        authorizationUtils.checkUserIsAssociatedWithLoggedTrainer(user);

        List<Macrocycle> userMacrocycles = getMacrocyclePortOut.getByUserEmail(user.getEmail());

        for (Macrocycle macrocycle : userMacrocycles) {

            if (!macrocycle.getUser().getEmail().equals(command.getUserEmail())) {
                log.error("Macrocycle ({}) does not belong to user ({})", macrocycle.getId(), command.getUserEmail());
                throw new AuthorizationException("Macrocycle not found for the specified user");
            }

            if (macrocycle.getId().equals(command.getMacrocycleId())) {
                macrocycle.activate();
            } else if (macrocycle.getStatus().equals(MacrocycleStatusEnum.ACTIVE)) {
                macrocycle.complete();
            }
        }

        return updateMacrocyclePortOut.updateAll(userMacrocycles);
    }


    private void checkCommand(ActivateMacrocycleCommand command) {
        checkAttribute.checkStringIsNotNullOrEmpty(command.getUserEmail(), "User email");
        checkAttribute.checkEmailFormat(command.getUserEmail(), "User email");
    }
}
