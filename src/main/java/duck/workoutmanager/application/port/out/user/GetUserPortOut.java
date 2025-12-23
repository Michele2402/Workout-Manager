package duck.workoutmanager.application.port.out.user;

import duck.workoutmanager.application.domain.model.User;

public interface GetUserPortOut {

    User getUserByEmail(String email);
}
