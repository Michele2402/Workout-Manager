package duck.workoutmanager.application.service.macrocycle;

import duck.workoutmanager.application.domain.exception.ObjectNotFoundException;
import duck.workoutmanager.application.domain.model.Macrocycle;
import duck.workoutmanager.application.port.in.macrocycle.GetActiveMacrocycleUseCase;
import duck.workoutmanager.application.port.out.macrocycle.GetMacrocyclePortOut;
import duck.workoutmanager.application.utils.AuthorizationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetActiveMacrocycleManagerService implements GetActiveMacrocycleUseCase {

    private final AuthorizationUtils authorizationUtils;

    private final GetMacrocyclePortOut getMacrocyclePortOut;


    @Override
    public Macrocycle getActiveMacrocycle() {

        String loggedUserEmail = authorizationUtils.getCurrentUserEmail();

        Macrocycle activeMacrocycle = getMacrocyclePortOut.getActiveWithMesocycles(loggedUserEmail);

        if (activeMacrocycle == null) {
            log.error("Active macrocycle not found for user ({})", loggedUserEmail);
            throw new ObjectNotFoundException("Macrocycle not found");
        }

        return activeMacrocycle;
    }
}
