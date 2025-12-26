package duck.workoutmanager.application.service.macrocycle;

import duck.workoutmanager.application.command.macrocycle.CreateMacrocycleCommand;
import duck.workoutmanager.application.domain.enums.MacrocycleStatusEnum;
import duck.workoutmanager.application.domain.exception.AlreadyExistsException;
import duck.workoutmanager.application.domain.exception.ObjectNotFoundException;
import duck.workoutmanager.application.domain.model.Macrocycle;
import duck.workoutmanager.application.domain.model.User;
import duck.workoutmanager.application.port.in.macrocycle.CreateMacrocycleUseCase;
import duck.workoutmanager.application.port.out.macrocycle.CreateMacrocyclePortOut;
import duck.workoutmanager.application.port.out.macrocycle.GetMacrocyclePortOut;
import duck.workoutmanager.application.port.out.user.CheckUserPortOut;
import duck.workoutmanager.application.port.out.user.GetUserPortOut;
import duck.workoutmanager.application.utils.AuthorizationUtils;
import duck.workoutmanager.application.utils.CheckAttribute;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateMacrocycleManagerService implements CreateMacrocycleUseCase {

    private final CheckAttribute checkAttribute;
    private final AuthorizationUtils authorizationUtils;

    private final GetUserPortOut getUserPortOut;
    private final GetMacrocyclePortOut getMacrocyclePortOut;
    private final CreateMacrocyclePortOut createMacrocyclePortOut;

    @Override
    public Macrocycle createMacrocycle(CreateMacrocycleCommand command) {

        checkCommand(command);

        User user = getUserPortOut.getByEmail(command.getUserEmail());

        if (user == null) {
            log.error("User with email ({}) not found", command.getUserEmail());
            throw new ObjectNotFoundException("User with email " + command.getUserEmail() + " not found");
        }

        authorizationUtils.checkUserIsAssociatedWithLoggedTrainer(user);

        List<Macrocycle> userMacrocycles = getMacrocyclePortOut.getByUserEmail(user.getEmail());

        boolean macrocycleWithSameNameExists = userMacrocycles.stream()
                .anyMatch(macrocycle -> macrocycle.getName().equalsIgnoreCase(command.getName()));

        if(macrocycleWithSameNameExists){
            log.error("Macrocycle with name ({}) already exists for user ({})", command.getName(), user.getEmail());
            throw new AlreadyExistsException("Macrocycle with the same name already exists for this user");
        }


        boolean hasAnyMacrocycle = userMacrocycles.stream()
                .anyMatch(macrocycle -> macrocycle.getStatus() == MacrocycleStatusEnum.ACTIVE);

        MacrocycleStatusEnum status = hasAnyMacrocycle ? MacrocycleStatusEnum.PLANNED : MacrocycleStatusEnum.ACTIVE;

        Macrocycle macrocycle = Macrocycle.builder()
                .id(UUID.randomUUID())
                .name(command.getName())
                .startDate(LocalDate.now())
                .expectedEndDate(command.getExpectedEndDate())
                .status(status)
                .user(user)
                .build();

        return createMacrocyclePortOut.create(macrocycle);
    }


    private void checkCommand(CreateMacrocycleCommand command) {

        checkAttribute.checkStringIsNotNullOrEmpty(command.getUserEmail(), "user email");
        checkAttribute.checkEmailFormat(command.getUserEmail(), "email");

        checkAttribute.checkStringIsNotNullOrEmpty(command.getName(), "macrocycle name");
        checkAttribute.checkStringIsShorterThan(command.getName(), 255, "macrocycle name");
    }
}
