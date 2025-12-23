package duck.workoutmanager.presentation.mapper;

import duck.workoutmanager.application.command.user.LoginCommand;
import duck.workoutmanager.application.command.user.RegisterCommand;
import duck.workoutmanager.presentation.request.user.LoginRequest;
import duck.workoutmanager.presentation.request.user.RegisterRequest;
import org.springframework.stereotype.Component;

@Component
public class UserPresentationMapper {

    public LoginCommand toCommand(LoginRequest request) {
        return LoginCommand.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
    }


    public RegisterCommand toCommand(RegisterRequest request) {
        return RegisterCommand.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .build();
    }
}
