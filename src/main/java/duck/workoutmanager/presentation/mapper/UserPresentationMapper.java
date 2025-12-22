package duck.workoutmanager.presentation.mapper;

import duck.workoutmanager.application.command.user.LoginCommand;
import duck.workoutmanager.presentation.request.user.LoginRequest;
import org.springframework.stereotype.Component;

@Component
public class UserPresentationMapper {

    public LoginCommand toCommand(LoginRequest request) {
        return LoginCommand.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
    }
}
