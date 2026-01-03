package duck.workoutmanager.application.port.out.mesocycle;

import duck.workoutmanager.application.domain.model.Mesocycle;

public interface UpdateMesocyclePortOut {
    Mesocycle update(Mesocycle mesocycle);
}
