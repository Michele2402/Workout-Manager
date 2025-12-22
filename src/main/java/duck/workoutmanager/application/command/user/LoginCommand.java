package duck.workoutmanager.application.command.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginCommand {
    private String email;
    private String password;
}
