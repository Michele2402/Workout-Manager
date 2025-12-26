package duck.workoutmanager.application.port.out.macrocycle;

import duck.workoutmanager.application.domain.model.Macrocycle;

import java.util.List;
import java.util.UUID;

public interface GetMacrocyclePortOut {
    Macrocycle getByIdWithUser(UUID macrocycleId);

    List<Macrocycle> getByUserEmail(String userEmail);
}
