package duck.workoutmanager.application.port.out.user;

import java.util.UUID;

public interface CheckUserPortOut {

    boolean checkUserHasAnyMacrocycle(String userEmail);
    boolean checkHasAnyMesocycleWithSameName(UUID macrocycleId, String mesocycleName);
}
