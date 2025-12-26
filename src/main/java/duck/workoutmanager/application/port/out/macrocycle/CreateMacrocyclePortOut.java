package duck.workoutmanager.application.port.out.macrocycle;

import duck.workoutmanager.application.domain.model.Macrocycle;

public interface CreateMacrocyclePortOut {

    Macrocycle create(Macrocycle macrocycle);
}
