package duck.workoutmanager.application.port.in.user;

import duck.workoutmanager.application.command.user.RegisterCommand;

public interface RegisterUserCase {

    void register(RegisterCommand command);
}
