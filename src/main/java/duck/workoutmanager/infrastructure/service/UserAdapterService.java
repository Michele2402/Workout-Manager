package duck.workoutmanager.infrastructure.service;

import duck.workoutmanager.application.domain.model.User;
import duck.workoutmanager.application.port.out.user.CreateUserPortOut;
import duck.workoutmanager.application.port.out.user.GetUserPortOut;
import duck.workoutmanager.infrastructure.entity.UserEntity;
import duck.workoutmanager.infrastructure.mapper.UserInfrastructureMapper;
import duck.workoutmanager.infrastructure.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserAdapterService implements GetUserPortOut, CreateUserPortOut {

    private final UserJpaRepository userJpaRepository;

    private final UserInfrastructureMapper userMapper;

    @Override
    public User getUserByEmail(String email) {
        log.info("Start - Get user by email: ({})", email);

        Optional<UserEntity> optionalUser = userJpaRepository.findByEmail(email);

        log.info("End - Get user by email: ({})", email);

        return optionalUser.map(userMapper::toModel).orElse(null);
    }

    @Override
    public void createUser(User user) {
        log.info("Start - Create user: ({})", user.getEmail());

        userJpaRepository.save(userMapper.toEntity(user));

        log.info("End - Create user: ({})", user.getEmail());
    }

}