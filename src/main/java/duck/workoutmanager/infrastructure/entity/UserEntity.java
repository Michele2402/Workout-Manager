package duck.workoutmanager.infrastructure.entity;

import duck.workoutmanager.application.domain.enums.RoleEnum;
import jakarta.persistence.*;
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

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    private String trainerEmail;

    @OneToMany(mappedBy = "user")
    private Set<MacrocycleEntity> macroCycles;

}
