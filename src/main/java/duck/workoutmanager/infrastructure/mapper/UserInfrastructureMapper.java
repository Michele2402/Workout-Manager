package duck.workoutmanager.infrastructure.mapper;

import duck.workoutmanager.application.domain.model.User;
import duck.workoutmanager.infrastructure.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserInfrastructureMapper {

    public User toModel(UserEntity entity) {
        return User.builder()
                .email(entity.getEmail())
                .password(entity.getPassword())
                .name(entity.getName())
                .surname(entity.getSurname())
                .role(entity.getRole())
                .trainerEmail(entity.getTrainerEmail())
                .build();
    }


    public UserEntity toEntity(User model) {
        return UserEntity.builder()
                .email(model.getEmail())
                .password(model.getPassword())
                .name(model.getName())
                .surname(model.getSurname())
                .role(model.getRole())
                .trainerEmail(model.getTrainerEmail())
                .build();
    }
}
