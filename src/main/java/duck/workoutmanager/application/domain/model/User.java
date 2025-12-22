package duck.workoutmanager.application.domain.model;

import duck.workoutmanager.application.domain.enums.RoleEnum;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@NoArgsConstructor
@Data
@Slf4j
public class User {

    private String email;
    private String password;
    private String name;
    private String surname;
    private RoleEnum role;

    private String trainerEmail;

    private Set<Macrocycle> macrocycles;

    @Builder
    public User(String email, String password, String name, String surname, RoleEnum role, String trainerEmail) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.trainerEmail = trainerEmail;
    }
}
