package duck.workoutmanager.application.port.out.mesocycle;

import duck.workoutmanager.application.domain.model.Mesocycle;

import java.util.UUID;

public interface GetMesocyclePortOut {

    Mesocycle getByIdWithUser(UUID mesocycleId);
}
