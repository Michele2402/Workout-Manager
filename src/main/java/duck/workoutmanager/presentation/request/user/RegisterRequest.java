package duck.workoutmanager.presentation.request.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String name;
    private String surname;
    private String email;
}