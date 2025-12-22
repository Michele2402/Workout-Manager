package duck.workoutmanager.application.port.in.user;

import duck.workoutmanager.application.command.user.LoginCommand;

public interface LoginUseCase {

    String login(LoginCommand command);
}
