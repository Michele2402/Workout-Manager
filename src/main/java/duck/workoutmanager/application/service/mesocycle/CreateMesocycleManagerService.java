package duck.workoutmanager.application.service.mesocycle;

import duck.workoutmanager.application.command.mesocycle.CreateMesocycleCommand;
import duck.workoutmanager.application.domain.exception.AlreadyExistsException;
import duck.workoutmanager.application.domain.exception.ObjectNotFoundException;
import duck.workoutmanager.application.domain.model.Macrocycle;
import duck.workoutmanager.application.domain.model.Mesocycle;
import duck.workoutmanager.application.domain.model.User;
import duck.workoutmanager.application.port.in.mesocycle.CreateMesocycleUseCase;
import duck.workoutmanager.application.port.out.macrocycle.GetMacrocyclePortOut;
import duck.workoutmanager.application.port.out.mesocycle.CreateMesocyclePortOut;
import duck.workoutmanager.application.port.out.user.CheckUserPortOut;
import duck.workoutmanager.application.utils.AuthorizationUtils;
import duck.workoutmanager.application.utils.CheckAttribute;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateMesocycleManagerService implements CreateMesocycleUseCase {

    private final CheckAttribute checkAttribute;
    private final AuthorizationUtils authorizationUtils;

    private final GetMacrocyclePortOut getMacrocyclePortOut;
    private final CheckUserPortOut checkUserPortOut;
    private final CreateMesocyclePortOut createMesocyclePortOut;


    @Override
    public Mesocycle create(CreateMesocycleCommand command) {

        checkCommand(command);

        Macrocycle macrocycle = getMacrocyclePortOut.getByIdWithUser(command.getMacrocycleId());

        if (macrocycle == null) {
            log.error("Macrocycle with id ({}) not found", command.getMacrocycleId());
            throw new ObjectNotFoundException("Macrocycle not found");
        }

        User user = macrocycle.getUser();

        authorizationUtils.checkUserIsAssociatedWithLoggedTrainer(user);

        boolean hasAnyMesocycleWithSameName = checkUserPortOut.checkHasAnyMesocycleWithSameName(
                macrocycle.getId(),
                command.getName()
        );

        if (hasAnyMesocycleWithSameName) {
            log.error("Mesocycle with name ({}) already exists for user ({})", command.getName(), user.getEmail());
            throw new AlreadyExistsException("Mesocycle with the same name already exists for this user");
        }


        Mesocycle mesocycle = Mesocycle.builder()
                .id(UUID.randomUUID())
                .name(command.getName())
                .startDate(LocalDate.now())
                .expectedEndDate(command.getExpectedEndDate())
                .daysPerWeek(command.getDaysPerWeek())
                .macrocycle(macrocycle)
                .build();

        mesocycle.generateWeeks(command.getTotalWeeks());

        return createMesocyclePortOut.create(mesocycle);
    }


    private void checkCommand(CreateMesocycleCommand command) {

        checkAttribute.checkStringIsNotNullOrEmpty(command.getName(), "Mesocycle name");
        checkAttribute.checkStringIsShorterThan(command.getName(), 100, "Mesocycle name");
        checkAttribute.checkDateIsNotInThePast(command.getExpectedEndDate(), "Expected end date");
        checkAttribute.checkNumberIsPositive(command.getDaysPerWeek(), "Days per week");
        checkAttribute.checkNumberIsPositive(command.getTotalWeeks(), "Total weeks");
        checkAttribute.checkNumberIsLessThan(command.getDaysPerWeek(), 8, "Days per week");
    }
}
