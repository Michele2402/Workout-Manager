package duck.workoutmanager.application.service.macrocycle;

import duck.workoutmanager.application.command.macrocycle.UpdateMacrocycleNameCommand;
import duck.workoutmanager.application.domain.exception.AlreadyExistsException;
import duck.workoutmanager.application.domain.exception.ObjectNotFoundException;
import duck.workoutmanager.application.domain.model.Macrocycle;
import duck.workoutmanager.application.domain.model.User;
import duck.workoutmanager.application.port.in.macrocycle.UpdateMacrocycleNameUseCase;
import duck.workoutmanager.application.port.out.macrocycle.GetMacrocyclePortOut;
import duck.workoutmanager.application.port.out.macrocycle.UpdateMacrocyclePortOut;
import duck.workoutmanager.application.utils.AuthorizationUtils;
import duck.workoutmanager.application.utils.CheckAttribute;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateMacrocycleNameManagerService implements UpdateMacrocycleNameUseCase {

    private final CheckAttribute checkAttribute;
    private final AuthorizationUtils authorizationUtils;

    private final UpdateMacrocyclePortOut updateMacrocyclePortOut;
    private final GetMacrocyclePortOut getMacrocyclePortOut;


    @Override
    public String updateMacrocycleName(UpdateMacrocycleNameCommand command) {

        checkCommand(command);

        Macrocycle macrocycle = getMacrocyclePortOut.getByIdWithUser(command.getMacrocycleId());

        if (macrocycle == null) {
            log.error("Macrocycle with id ({}) not found", command.getMacrocycleId());
            throw new ObjectNotFoundException("Macrocycle not found");
        }

        User user = macrocycle.getUser();
        authorizationUtils.checkUserIsAssociatedWithLoggedTrainer(user);


        List<Macrocycle> userMacrocycles = getMacrocyclePortOut.getByUserEmail(user.getEmail());

        boolean macrocycleWithSameNameExists = userMacrocycles.stream()
                .filter(m -> !m.getId().equals(macrocycle.getId()))
                .anyMatch(m -> m.getName().equalsIgnoreCase(command.getName()));

        if(macrocycleWithSameNameExists){
            log.error("Macrocycle with name ({}) already exists for user ({})", command.getName(), user.getEmail());
            throw new AlreadyExistsException("Macrocycle with the same name already exists for this user");
        }


        macrocycle.setName(command.getName());

        Macrocycle updatedMacrocycle = updateMacrocyclePortOut.update(macrocycle);

        return updatedMacrocycle.getName();
    }

    private void checkCommand(UpdateMacrocycleNameCommand command) {
        checkAttribute.checkStringIsNotNullOrEmpty(command.getName(), "Macrocycle name");
        checkAttribute.checkStringIsShorterThan(command.getName(), 255, "Macrocycle name");
    }
}
