package duck.workoutmanager.infrastructure.entity;

import duck.workoutmanager.application.domain.enums.RoleEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "user_entity")
public class UserEntity {

    @Id
    private String email;
    private String password;
    private String name;
    private String surname;
    private RoleEnum role;

    private String trainerEmail;

    @OneToMany(mappedBy = "user")
    private Set<MacrocycleEntity> macroCycles;

}
