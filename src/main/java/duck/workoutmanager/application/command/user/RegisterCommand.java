package duck.workoutmanager.application.command.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterCommand {

    private String name;
    private String surname;
    private String email;
}
